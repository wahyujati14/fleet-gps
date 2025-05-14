-- Table: vehicle
CREATE TABLE vehicle (
    id SERIAL PRIMARY KEY,
    plate_number VARCHAR(20) NOT NULL UNIQUE,
    name VARCHAR(50) NOT NULL,
    type VARCHAR(30)
);

-- Table: gps_log
CREATE TABLE gps_log (
    id SERIAL PRIMARY KEY,
    vehicle_id INTEGER NOT NULL REFERENCES vehicle(id),
    latitude DOUBLE PRECISION,
    longitude DOUBLE PRECISION,
    speed DOUBLE PRECISION,
    timestamp TIMESTAMP NOT NULL,
    speed_violation BOOLEAN,
    CONSTRAINT fk_vehicle FOREIGN KEY(vehicle_id) REFERENCES vehicle(id)
);

-- Indexes
CREATE INDEX idx_vehicle_timestamp ON gps_log(vehicle_id, timestamp);
CREATE INDEX idx_speed_violation ON gps_log(speed_violation);