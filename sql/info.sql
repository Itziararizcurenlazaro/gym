-- TRAINERS
INSERT INTO trainers (name, speciality, experience_years, email, phone)
VALUES
('John Carter', 'Strength Training', 5, 'john@gym.com', '123456789'),
('Emily Davis', 'Cardio', 3, 'emily@gym.com', '987654321'),
('Michael Brown', 'Yoga & Mobility', 7, 'michael@gym.com', '654987321');

-- WORKOUTS
INSERT INTO workouts (name, difficulty, duration, description, trainer_id)
VALUES
('Full Body Workout', 'Intermediate', 60,
'Complete full body strength routine', 1),

('HIIT Cardio', 'Advanced', 45,
'High intensity interval training session', 2),

('Yoga Flow', 'Beginner', 50,
'Relaxing yoga and stretching exercises', 3),

('Upper Body Blast', 'Intermediate', 55,
'Focused upper body strength workout', 1),

('Core Training', 'Beginner', 40,
'Core stability and abdominal exercises', 2);

-- USERS
INSERT INTO users (name, email, age, fitness_goal, membership_type)
VALUES
('Alex Johnson', 'alex@mail.com', 24, 'Lose Weight', 'Premium'),

('Sarah Miller', 'sarah@mail.com', 29, 'Gain Muscle', 'VIP'),

('Daniel Wilson', 'daniel@mail.com', 31, 'Improve Endurance', 'Basic'),

('Emma Thompson', 'emma@mail.com', 22, 'Stay Fit', 'Premium'),

('Chris Evans', 'chris@mail.com', 35, 'Build Strength', 'VIP');