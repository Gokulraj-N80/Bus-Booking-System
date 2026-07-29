-- Insert Admin User
INSERT INTO users (email, role, password) 
VALUES ('admin', 'ADMIN', 'password123') 
ON DUPLICATE KEY UPDATE role='ADMIN';

-- Insert Some Default Buses for Tamil Nadu
INSERT INTO bus (bus_name, source, destination, departure_time, departure_date, seats)
VALUES 
('SETC-Chennai', 'Chennai', 'Madurai', '08:00 PM', '2023-11-20', 40),
('KPN-Travels', 'Coimbatore', 'Salem', '06:30 AM', '2023-11-20', 30),
('SRS-Travels', 'Trichy', 'Chennai', '10:00 PM', '2023-11-21', 50),
('TNSTC-Express', 'Madurai', 'Tirunelveli', '02:00 PM', '2023-11-21', 40),
('Parveen-Travels', 'Chennai', 'Bangalore', '11:00 PM', '2023-11-22', 45)
ON DUPLICATE KEY UPDATE source=source;
