CREATE USER paw WITH PASSWORD 'paw';

GRANT ALL PRIVILEGES ON DATABASE paw1 to paw;

GRANT ALL PRIVILEGES ON TABLE users to paw;

GRANT ALL PRIVILEGES ON TABLE properties to paw;

GRANT ALL PRIVILEGES ON TABLE pictures to paw;

GRANT ALL PRIVILEGES ON TABLE pictures_id_seq to paw;

GRANT ALL PRIVILEGES ON TABLE properties_id_seq to paw;

GRANT ALL PRIVILEGES ON TABLE users_id_seq to paw;
