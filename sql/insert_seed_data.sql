INSERT INTO specialisation (name) VALUES ('Cardiology'), ('Neurology'), ('Orthopedics'), ('Pediatrics'), ('Dermatology'), ('Radiology'), ('Gastroenterology'), ('Psychiatry'), ('Ophthalmology'), ('Anesthesiology'), ('Endocrinology'), ('Urology'), ('Nephrology'), ('Hematology'), ('Allergology'), ('Immunology'), ('General Medicine'), ('General Surgery'), ('Pathology'), ('Ear, Nose, and Throat (ENT)');

INSERT INTO doctor (name, specialisation_id, years_of_experience, shift_start_time, shift_end_time, is_active) VALUES
('Dr. Rajesh Kumar', 1, 10, '08:00:00', '16:00:00', 1),
('Dr. Priya Sharma', 2, 5, '09:00:00', '17:00:00', 1),
('Dr. Arun Verma', 3, 15, '10:00:00', '18:00:00', 1),
('Dr. Neha Patel', 4, 8, '07:00:00', '15:00:00', 1),
('Dr. Sunil Gupta', 5, 12, '11:00:00', '19:00:00', 1),
('Dr. Kavita Reddy', 6, 6, '08:00:00', '16:00:00', 1),
('Dr. Ravi Yadav', 7, 11, '09:00:00', '17:00:00', 1),
('Dr. Aarti Deshmukh', 8, 7, '08:00:00', '16:00:00', 1),
('Dr. Vijay Singh', 9, 9, '09:00:00', '17:00:00', 1),
('Dr. Suman Joshi', 10, 14, '10:00:00', '18:00:00', 1),
('Dr. Manoj Bhat', 11, 18, '07:00:00', '15:00:00', 1),
('Dr. Rekha Iyer', 12, 4, '08:00:00', '16:00:00', 1),
('Dr. Anil Mehta', 13, 13, '09:00:00', '17:00:00', 1),
('Dr. Seema Khanna', 14, 16, '10:00:00', '18:00:00', 1),
('Dr. Ramesh Kumar', 15, 10, '07:00:00', '15:00:00', 1),
('Dr. Shalini Mishra', 16, 5, '09:00:00', '17:00:00', 1),
('Dr. Harish Agarwal', 17, 3, '08:00:00', '16:00:00', 1),
('Dr. Manju Nair', 18, 7, '10:00:00', '18:00:00', 1),
('Dr. Ankit Singh', 19, 9, '09:00:00', '17:00:00', 1),
('Dr. Pooja Kapoor', 20, 20, '07:00:00', '15:00:00', 1),
('Dr. Meenal Pandey', 1, 9, '08:00:00', '16:00:00', 1),
('Dr. Mohan Rathi', 2, 12, '09:00:00', '17:00:00', 1),
('Dr. Arvind Raghavan', 3, 10, '10:00:00', '18:00:00', 1),
('Dr. Jyoti Sharma', 4, 8, '07:00:00', '15:00:00', 1),
('Dr. Krishanlal Mehta', 5, 15, '11:00:00', '19:00:00', 1),
('Dr. Rupa Saha', 6, 5, '08:00:00', '16:00:00', 1),
('Dr. Laxman Dhamija', 7, 12, '09:00:00', '17:00:00', 1),
('Dr. Shilpa Jadhav', 8, 10, '08:00:00', '16:00:00', 1),
('Dr. Amit Agarwal', 9, 11, '09:00:00', '17:00:00', 1),
('Dr. Rajeev Mishra', 10, 13, '10:00:00', '18:00:00', 1),
('Dr. Snehal Soni', 11, 8, '07:00:00', '15:00:00', 1),
('Dr. Bhavna Jain', 12, 6, '08:00:00', '16:00:00', 1),
('Dr. Jagdish Yadav', 13, 7, '09:00:00', '17:00:00', 1),
('Dr. Devendra Sharma', 14, 9, '10:00:00', '18:00:00', 1),
('Dr. Namrata Kaur', 15, 5, '07:00:00', '15:00:00', 1),
('Dr. Shubham Prasad', 16, 6, '09:00:00', '17:00:00', 1),
('Dr. Sushma Bhardwaj', 17, 4, '08:00:00', '16:00:00', 1),
('Dr. Arun Kumar', 18, 14, '10:00:00', '18:00:00', 1),
('Dr. Aishwarya Rani', 19, 5, '09:00:00', '17:00:00', 1),
('Dr. Subash Chandra', 20, 12, '07:00:00', '15:00:00', 1);

INSERT INTO patient (name, date_of_birth, gender, phone_number, is_active) VALUES
('Aarav Patel', '1993-01-11', 'MALE', '9876543210', 1),
('Priya Gupta', '1979-08-19', 'FEMALE', '9123456789', 1),
('Amit Sharma', '1997-04-28', 'MALE', '9056781234', 1),
('Sanya Iyer', '1990-04-22', 'FEMALE', '9345678901', 1),
('Rohan Deshmukh', '1965-02-01', 'MALE', '9876545678', 1),
('Tanya Verma', '1999-10-25', 'FEMALE', '9056123456', 1),
('Raj Kumar', '1975-02-06', 'MALE', '9283745610', 1),
('Madhavi Yadav', '1984-07-30', 'FEMALE', '8765432109', 1),
('Kunal Singh', '1991-11-01', 'MALE', '9112345678', 1),
('Shreya Bhat', '1987-05-11', 'FEMALE', '9234567890', 1),
('Anjali Joshi', '1969-07-17', 'FEMALE', '9345678902', 1),
('Aakash Reddy', '1996-05-16', 'MALE', '9102345678', 1),
('Neelam Kapoor', '1976-11-21', 'FEMALE', '9276543210', 1),
('Vinay Khanna', '1994-10-17', 'MALE', '9405678901', 1),
('Poonam Mishra', '1983-10-06', 'FEMALE', '9265432109', 1),
('Deepak Sharma', '2003-03-31', 'MALE', '9328745610', 1),
('Sushma Patel', '1982-06-08', 'FEMALE', '9082736541', 1),
('Kartik Sharma', '1997-07-20', 'MALE', '9234560987', 1),
('Meera Nair', '1974-05-24', 'FEMALE', '9245670912', 1),
('Ravi Joshi', '2003-03-26', 'MALE', '9134567890', 1),
('Neha Verma', '1986-03-26', 'FEMALE', '9123678901', 1),
('Akash Kumar', '1984-01-12', 'MALE', '9223745610', 1),
('Sonal Singh', '1983-07-13', 'FEMALE', '9203745678', 1),
('Parth Bhat', '1991-02-14', 'MALE', '9324567890', 1),
('Hina Agarwal', '1969-10-13', 'FEMALE', '9376543123', 1),
('Rishi Iyer', '1994-06-07', 'MALE', '9182746530', 1),
('Sonal Joshi', '1990-10-21', 'FEMALE', '9234765812', 1),
('Gauri Patel', '1976-08-28', 'FEMALE', '9052346751', 1),
('Vikram Yadav', '1995-09-02', 'MALE', '9123465738', 1),
('Pragati Khanna', '1994-12-04', 'FEMALE', '9314568724', 1),
('Karan Mishra', '1989-03-27', 'MALE', '9182374615', 1),
('Deepika Reddy', '1998-07-28', 'FEMALE', '9336574521', 1),
('Manoj Reddy', '1985-05-17', 'MALE', '9402785364', 1),
('Suman Deshmukh', '1993-10-03', 'FEMALE', '9234756108', 1),
('Kriti Sharma', '1970-01-09', 'FEMALE', '9123746517', 1),
('Ashwin Gupta', '1999-09-11', 'MALE', '9203745618', 1),
('Pallavi Reddy', '1983-11-15', 'FEMALE', '9234567892', 1),
('Vinayak Joshi', '1982-07-08', 'MALE', '9314786532', 1),
('Pranjal Mishra', '1990-03-28', 'MALE', '9012786543', 1),
('Sushil Agarwal', '1974-10-05', 'MALE', '9123450987', 1),
('Tanuja Verma', '1994-09-03', 'FEMALE', '9034875612', 1),
('Dinesh Bhat', '1976-12-20', 'MALE', '9356821470', 1),
('Aarti Deshmukh', '1996-10-05', 'FEMALE', '9265478921', 1),
('Ankit Yadav', '1986-06-18', 'MALE', '9224786531', 1),
('Rina Singh', '2002-12-20', 'FEMALE', '9345612789', 1),
('Nikhil Khanna', '1983-06-03', 'MALE', '9023456712', 1),
('Kajal Patel', '1986-01-11', 'FEMALE', '9223456987', 1),
('Siddharth Yadav', '1992-11-08', 'MALE', '9012345698', 1),
('Manju Sharma', '1970-02-25', 'FEMALE', '9384736512', 1),
('Krishna Iyer', '1977-06-27', 'MALE', '9182746539', 1),
('Tanu Sharma', '1980-12-28', 'FEMALE', '9203456749', 1),
('Jaya Khanna', '1989-09-02', 'FEMALE', '9265478932', 1),
('Amit Agarwal', '1996-05-29', 'MALE', '9334654789', 1),
('Neelam Kapoor', '1985-04-12', 'FEMALE', '9013456780', 1),
('Chandan Bhat', '1998-06-02', 'MALE', '9058765431', 1),
('Vishal Gupta', '1976-02-28', 'MALE', '9345612345', 1),
('Suman Deshmukh', '1988-11-26', 'FEMALE', '9084765321', 1),
('Rohit Sharma', '1995-12-21', 'MALE', '9235476871', 1),
('Shalini Agarwal', '1992-01-24', 'FEMALE', '9024765132', 1),
('Karan Patel', '1971-01-21', 'MALE', '9103745619', 1),
('Nisha Gupta', '1979-12-21', 'FEMALE', '9183746591', 1),
('Rahul Bhat', '2000-10-15', 'MALE', '9234765813', 1),
('Nikita Sharma', '1986-09-17', 'FEMALE', '9052346753', 1),
('Ravi Kapoor', '1996-04-21', 'MALE', '9123465739', 1),
('Amit Joshi', '1995-05-18', 'MALE', '9314568725', 1),
('Deepika Kumar', '1998-10-18', 'FEMALE', '9336574621', 1),
('Ankit Bhat', '1983-07-09', 'MALE', '9052346752', 1),
('Neelam Sharma', '1975-04-22', 'FEMALE', '9234756109', 1),
('Shiv Kumar', '1978-01-06', 'MALE', '9183746539', 1),
('Kiran Soni', '1983-03-28', 'FEMALE', '9072746512', 1),
('Shweta Khurana', '1986-10-14', 'FEMALE', '9024785392', 1),
('Sandeep Reddy', '1992-10-28', 'MALE', '9043756812', 1),
('Satyam Verma', '1998-09-15', 'MALE', '9182746581', 1),
('Disha Kapoor', '1994-12-24', 'FEMALE', '9273456802', 1),
('Aditya Mehta', '1990-01-02', 'MALE', '9235678901', 1);
