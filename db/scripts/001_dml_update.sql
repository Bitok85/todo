
INSERT INTO priorities (name, position) VALUES ('urgently', 1);
INSERT INTO priorities (name, position) VALUES ('normal', 2);

UPDATE task SET priority_id = (SELECT id FROM priorities WHERE name = 'urgently');