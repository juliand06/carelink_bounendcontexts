-- Security module tables

CREATE TABLE IF NOT EXISTS ${db_schema}.roles (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(50) NOT NULL UNIQUE,
    authority VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS ${db_schema}.auth_users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    email VARCHAR(150) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

CREATE TABLE IF NOT EXISTS ${db_schema}.user_roles (
    user_id UUID NOT NULL,
    role_id UUID NOT NULL,
    PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_user_roles_user_id FOREIGN KEY (user_id) REFERENCES ${db_schema}.auth_users (id) ON DELETE CASCADE,
    CONSTRAINT fk_user_roles_role_id FOREIGN KEY (role_id) REFERENCES ${db_schema}.roles (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS ${db_schema}.refresh_tokens (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL,
    token VARCHAR(255) NOT NULL UNIQUE,
    expires_at TIMESTAMPTZ NOT NULL,
    revoked BOOLEAN NOT NULL DEFAULT FALSE,
    CONSTRAINT fk_refresh_tokens_user_id FOREIGN KEY (user_id) REFERENCES ${db_schema}.auth_users (id) ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_auth_users_email ON ${db_schema}.auth_users (email);
CREATE INDEX IF NOT EXISTS idx_user_roles_user_id ON ${db_schema}.user_roles (user_id);
CREATE INDEX IF NOT EXISTS idx_user_roles_role_id ON ${db_schema}.user_roles (role_id);
CREATE INDEX IF NOT EXISTS idx_refresh_tokens_user_id ON ${db_schema}.refresh_tokens (user_id);
CREATE INDEX IF NOT EXISTS idx_refresh_tokens_token ON ${db_schema}.refresh_tokens (token);

INSERT INTO ${db_schema}.roles (id, name, authority) VALUES
    ('550e8400-e29b-41d4-a716-446655440001', 'USER', 'ROLE_USER'),
    ('550e8400-e29b-41d4-a716-446655440002', 'ADMIN', 'ROLE_ADMIN'),
    ('550e8400-e29b-41d4-a716-446655440003', 'MODERATOR', 'ROLE_MODERATOR')
ON CONFLICT (name) DO NOTHING;
