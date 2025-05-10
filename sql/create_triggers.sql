DELIMITER $$

CREATE TRIGGER check_doctor_appointment_overlap
BEFORE INSERT ON appointment
FOR EACH ROW
BEGIN
  IF EXISTS (
    SELECT 1
    FROM appointment
    WHERE doctor_id = NEW.doctor_id
      AND appointment_date = NEW.appointment_date
      AND (
        (NEW.start_time < end_time AND NEW.start_time >= start_time) OR
        (NEW.end_time > start_time AND NEW.end_time <= end_time) OR
        (NEW.start_time <= start_time AND NEW.end_time >= end_time)
      )
  ) THEN
    SIGNAL SQLSTATE '45000'
    SET MESSAGE_TEXT = 'Doctor already has an overlapping appointment.';
  END IF;
END$$

DELIMITER ;


DELIMITER $$

CREATE TRIGGER check_patient_appointment_overlap
BEFORE INSERT ON appointment
FOR EACH ROW
BEGIN
  IF EXISTS (
    SELECT 1
    FROM appointment
    WHERE patient_id = NEW.patient_id
      AND appointment_date = NEW.appointment_date
      AND (
        (NEW.start_time < end_time AND NEW.start_time >= start_time) OR
        (NEW.end_time > start_time AND NEW.end_time <= end_time) OR
        (NEW.start_time <= start_time AND NEW.end_time >= end_time)
      )
  ) THEN
    SIGNAL SQLSTATE '45000'
    SET MESSAGE_TEXT = 'Patient already has an overlapping appointment.';
  END IF;
END$$

DELIMITER ;
