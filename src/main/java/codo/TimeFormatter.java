package codo;

public class TimeFormatter {

    public static String formatDuration(int seconds) {
        if (seconds == 0) {
            return "now";
        }
        int _seconds = seconds % 60;
        int _minutes = (seconds / 60) % 60;
        int _hours = (seconds / 60 / 60) % 24;
        int _days = (seconds / 60 / 60 / 24) % 365;
        int _years = (seconds / 60 / 60 / 24 / 365);
        StringBuilder formattedTimeStringBuilder = new StringBuilder();
        if (_years == 1) {
            formattedTimeStringBuilder.append(_years).append(" year");
        } else if (_years > 1) {
            formattedTimeStringBuilder.append(_years).append(" years");
        }
        if (_days == 1) {
            if (!formattedTimeStringBuilder.isEmpty() & (_hours + _minutes + _seconds != 0)) {
                formattedTimeStringBuilder.append(", ");
            } else if (!formattedTimeStringBuilder.isEmpty()) {
                formattedTimeStringBuilder.append(" and ");
            }
            formattedTimeStringBuilder.append(_days).append(" day");
        } else if (_days > 1) {
            if (!formattedTimeStringBuilder.isEmpty() & (_minutes + _seconds != 0)) {
                formattedTimeStringBuilder.append(", ");
            } else if (!formattedTimeStringBuilder.isEmpty()) {
                formattedTimeStringBuilder.append(" and ");
            }
            formattedTimeStringBuilder.append(_days).append(" days");
        }
        if (_hours == 1) {
            if (!formattedTimeStringBuilder.isEmpty() & (_minutes + _seconds != 0)) {
                formattedTimeStringBuilder.append(", ");
            } else if (!formattedTimeStringBuilder.isEmpty()) {
                formattedTimeStringBuilder.append(" and ");
            }
            formattedTimeStringBuilder.append(_hours).append(" hour");
        } else if (_hours > 1) {
            if (!formattedTimeStringBuilder.isEmpty() & (_minutes + _seconds != 0)) {
                formattedTimeStringBuilder.append(", ");
            } else if (!formattedTimeStringBuilder.isEmpty()){
                formattedTimeStringBuilder.append(" and ");
            }
            formattedTimeStringBuilder.append(_hours).append(" hours");
        }
        if (_minutes == 1) {
            if (!formattedTimeStringBuilder.isEmpty() & (_seconds != 0)) {
                formattedTimeStringBuilder.append(", ");
            } else if (!formattedTimeStringBuilder.isEmpty()){
                formattedTimeStringBuilder.append(" and ");
            }
            formattedTimeStringBuilder.append(_minutes).append(" minute");
        } else if (_minutes > 1) {
            if (!formattedTimeStringBuilder.isEmpty() & (_seconds != 0)) {
                formattedTimeStringBuilder.append(", ");
            } else if (!formattedTimeStringBuilder.isEmpty()) {
                formattedTimeStringBuilder.append(" and ");
            }
            formattedTimeStringBuilder.append(_minutes).append(" minutes");
        }
        if (_seconds == 1) {
            if (!formattedTimeStringBuilder.isEmpty()) {
                formattedTimeStringBuilder.append(" and ");
            }
            formattedTimeStringBuilder.append(_seconds).append(" second");
        } else if (_seconds > 1) {
            if (!formattedTimeStringBuilder.isEmpty()) {
                formattedTimeStringBuilder.append(" and ");
            }
            formattedTimeStringBuilder.append(_seconds).append(" seconds");
        }
        return formattedTimeStringBuilder.toString();
    }
}