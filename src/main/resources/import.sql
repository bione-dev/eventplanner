-- Inserir eventos
INSERT INTO Event (id, name, date, location) VALUES
(1, 'Tech Conference 2025', '2025-02-15T09:00:00', 'New York Convention Center'),
(2, 'Music Festival', '2025-03-10T17:00:00', 'Central Park, NYC'),
(3, 'Startup Pitch Night', '2025-04-05T18:30:00', 'Silicon Valley Auditorium');

-- Inserir participantes para o evento 1
INSERT INTO Participant (id, name, email) VALUES
(1, 'Alice Johnson', 'alice.johnson@example.com'),
(2, 'Bob Smith', 'bob.smith@example.com'),
(3, 'Carol Lee', 'carol.lee@example.com');

-- Relacionar participantes ao evento 1
INSERT INTO Event_participants (Event_id, participants_id) VALUES
(1, 1),
(1, 2),
(1, 3);

-- Inserir participantes para o evento 2
INSERT INTO Participant (id, name, email) VALUES
(4, 'David Brown', 'david.brown@example.com'),
(5, 'Eve Davis', 'eve.davis@example.com');

-- Relacionar participantes ao evento 2
INSERT INTO Event_participants (Event_id, participants_id) VALUES
(2, 4),
(2, 5);

-- Ajustar o próximo valor do ID da tabela Event
ALTER TABLE Event ALTER COLUMN id RESTART WITH 4;

-- Ajustar o próximo valor do ID da tabela Participant (opcional, se novos participantes forem criados)
ALTER TABLE Participant ALTER COLUMN id RESTART WITH 6;
