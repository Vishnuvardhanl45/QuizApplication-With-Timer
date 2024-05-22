class TimerHelper {
    private java.util.Timer timer;
    private int timeRemaining;

    public void startTimer(int durationInSeconds, Runnable task) {
        timeRemaining = durationInSeconds;
        timer = new java.util.Timer();
        timer.scheduleAtFixedRate(new java.util.TimerTask() {
            @Override
            public void run() {
                if (timeRemaining > 0) {
                    displayTimeRemaining();
                    timeRemaining--;
                } else {
                    cancelTimer();
                    task.run();
                }
            }
        }, 0, 1000); // Update every 1 second
    }

    public void cancelTimer() {
        if (timer != null) {
            timer.cancel();
        }
    }

    private void displayTimeRemaining() {
        System.out.print("\rTime remaining: " + timeRemaining + " seconds. Enter the correct option: ");
    }
    
}