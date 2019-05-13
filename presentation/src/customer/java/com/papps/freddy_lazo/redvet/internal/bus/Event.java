package com.papps.freddy_lazo.redvet.internal.bus;

import com.papps.freddy_lazo.redvet.internal.bus.event.BaseEvent;

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

    public static class NotificationChatEvent {

        private final int appointmentId;

        public NotificationChatEvent(int appointmentId) {
            this.appointmentId = appointmentId;
        }

        public int getAppointmentId() {
            return appointmentId;
        }
    }

    public static class SuccessAppointment{

        public SuccessAppointment() {
        }
    }
}
