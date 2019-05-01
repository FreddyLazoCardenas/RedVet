package com.papps.freddy_lazo.redvet.internal.bus.event;

public class Event extends BaseEvent {

    public Event() {
    }

    public static class NotificationEvent {

        private final int appointmentId;

        public NotificationEvent(int appointmentId) {
            this.appointmentId = appointmentId;
        }

        public int getAppointmentId() {
            return appointmentId;
        }
    }
}
