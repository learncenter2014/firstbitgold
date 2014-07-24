package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SimpleProcessRunner executes a single command that takes no input.
 *
 * The process runner captures stdout, stderr, and return code from the command
 * process that it executes. Client applications can request the these data
 * values as well as start, end, and elapsed times.
 */
public class SimpleProcessRunner implements Runnable {

    /** Logger */
    private static final Logger logger = LoggerFactory.getLogger(SimpleProcessRunner.class);

    /** Newline string. */
    private static final String NEWLINE = "\n";

    /** Indicates failure > 0 */
    private static final int PROCESS_FAILED = 10;

    /** Standard output. */
    private String processStdOut = "";

    /** Standard error. */
    private String processStdErr = "";

    /** Process start time. */
    private Date processStartTime;

    /** Process end time. */
    private Date processEndTime;

    /** Process return code. */
    private int processReturnCode = PROCESS_FAILED;

    /** Process command string. */
    private String commandString = null;

    /** Process command array. */
    private String[] commandArray = null;

    /**
     * Indicates that processing should use command array instead of command
     * string.
     */
    private boolean useCommandArray = false;

    /** Indicates that processing will timeout after timeoutInSeconds. */
    private long timeoutInSeconds = 0;

    /**
     * Constructs a simple process runner given a simple o/s command string.
     *
     * @param commandString
     *            the command to execute
     */
    public SimpleProcessRunner(String commandString) {
        this.commandString = commandString;
    }

    /**
     * Constructs a simple process runner given a command array of strings.
     *
     * @param commandArray
     *            command string array containing command and arguments
     */
    public SimpleProcessRunner(String[] commandArray) {
        this.commandArray = commandArray;
        useCommandArray = true;
    }

    public SimpleProcessRunner(String commandString, long timeoutInSeconds) {
        this.commandString = commandString;
        this.timeoutInSeconds = timeoutInSeconds;
    }

    /**
     * Executes the command.
     *
     * @see java.lang.Runnable#run()
     */
    public void run() {
        processStartTime = new Date();

        Process process = null;

        try {
            Runtime runtime = Runtime.getRuntime();

            // execute the process
            if (useCommandArray) {
                process = runtime.exec(commandArray);
            } else {
                process = runtime.exec(commandString);
            }

            StreamGobbler outputGobbler = new StreamGobbler(process.getInputStream());
            StreamGobbler errorGobbler = new StreamGobbler(process.getErrorStream());

            outputGobbler.start();
            errorGobbler.start();

            if (timeoutInSeconds <= 0) {
                outputGobbler.join();
                errorGobbler.join();
                process.waitFor();
            } else {
                long now = System.currentTimeMillis();
                long timeoutInMillis = 1000L * timeoutInSeconds;
                long finish = now + timeoutInMillis;
                while (isAlive(process) && (System.currentTimeMillis() < finish)) {
                    Thread.sleep(10);
                }
                if (isAlive(process)) {
                    throw new InterruptedException("Process timeout out after " + timeoutInSeconds + " seconds");
                }
                outputGobbler.join();
                errorGobbler.join();
            }

            processReturnCode = process.exitValue();
            processStdOut = outputGobbler.getStreamData();
            processStdErr = errorGobbler.getStreamData();
        } catch (Throwable t) {
            processStdErr += t.getMessage();
            processReturnCode = PROCESS_FAILED;
        } finally {
            if (process != null) {
                process.destroy();
            }
            processEndTime = new Date();
        }
    }

    private boolean isAlive(Process p) {
        try {
            p.exitValue();
            return false;
        } catch (IllegalThreadStateException e) {
            return true;
        }
    }

    /**
     * Gets standard output string from the process.
     *
     * @return the stdout string
     */
    public String getStdOut() {
        return processStdOut;
    }

    /**
     * Gets standard error string from the process.
     *
     * @return the stderr string
     */
    public String getStdErr() {
        return processStdErr;
    }

    /**
     * Gets the return code from the process.
     *
     * @return the return code (non-zero is failure)
     */
    public int getRc() {
        return processReturnCode;
    }

    /**
     * Gets the process start time.
     *
     * @return the process start time
     */
    public Date getStartTime() {
        return processStartTime;
    }

    /**
     * Gets the process end time.
     *
     * @return the process end time
     */
    public Date getEndTime() {
        return processEndTime;
    }

    /**
     * Gets the process elapsed time.
     *
     * @return the process elapsed time
     */
    public long getElapsedTime() {
        if (processStartTime == null || processEndTime == null) {
            throw new IllegalStateException("Start/end times are not set.");
        }

        return processEndTime.getTime() - processStartTime.getTime();
    }

    /**
     * Gets the command string that will be/was executed.
     *
     * @return the command string
     */
    public String getCommandString() {
        if (useCommandArray) {
            StringBuilder builder = new StringBuilder();
            for (String command : commandArray) {
                builder.append(command);
                builder.append(" ");
            }

            return builder.toString();
        } else {
            return commandString;
        }
    }

    /**
     * Dumps the output in debug mode.
     */
    public void debugDump() {
        if (logger.isDebugEnabled()) {
            logger.debug("Command : {}", getCommandString());
            logger.debug("System Return Code: {}", getRc());
            logger.debug("Start time: {}", getStartTime());
            logger.debug("End time: {}", getEndTime());
            logger.debug("Elapsed time(ms): {}", getElapsedTime());
            logger.debug("Stdout: {}", getStdOut());
            logger.debug("Stderr: {}", getStdErr());
        }
    }

    /**
     * Gets complete run results as a string.
     *
     * @return run results as string
     */
    public String getRunResults() {
        StringBuilder buf = new StringBuilder();
        buf.append(this.getClass().getCanonicalName()).append(" Execution Results --").append(NEWLINE);
        buf.append("Command: ").append(getCommandString()).append(NEWLINE);
        buf.append("System Return Code: ").append(getRc()).append(NEWLINE);
        buf.append("Start time: ").append(getStartTime()).append(NEWLINE);
        buf.append("End time: ").append(getEndTime()).append(NEWLINE);
        buf.append("Elapsed time(ms): ").append(getElapsedTime()).append(NEWLINE);
        buf.append("Stdout: ").append(getStdOut()).append(NEWLINE);
        buf.append("Stderr: ").append(getStdErr()).append(NEWLINE);

        return buf.toString();
    }

    /**
     * Gobbles up stream data from stdout and stderr of external process.
     */
    private static class StreamGobbler extends Thread {

        /** Input stream. */
        private InputStream is;

        /** Builds up data from stream. */
        private StringBuilder builder = new StringBuilder();

        /**
         * Constructs the gobbler given an input stream.
         *
         * @param is
         *            input stream
         */
        public StreamGobbler(InputStream is) {
            this.is = is;
        }

        /**
         * @see java.lang.Thread#run()
         */
        @Override
        public void run() {
            try {
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String line = null;

                while ((line = br.readLine()) != null) {
                    // build up string util EOF
                    builder.append(line).append("\n");
                }
            } catch (IOException ioe) {
                throw new RuntimeException(ioe);
            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        // ignore; best effort
                    }
                }
            }
        }

        /**
         * Gets the stream's data.
         *
         * @return string representation of the stream's data
         */
        public String getStreamData() {
            return builder.toString();
        }
    }

}
