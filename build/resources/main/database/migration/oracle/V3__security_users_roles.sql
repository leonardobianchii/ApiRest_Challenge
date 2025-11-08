-- ===== V3: Segurança (idempotente) =====

-- Cria tabela, ignorando ORA-00955 (já existe)
BEGIN
  EXECUTE IMMEDIATE '
    CREATE TABLE USUARIOS (
      id       NUMBER PRIMARY KEY,
      username VARCHAR2(100) UNIQUE NOT NULL,
      password VARCHAR2(100) NOT NULL,
      enabled  NUMBER(1) DEFAULT 1 NOT NULL
    )';
EXCEPTION WHEN OTHERS THEN IF SQLCODE != -955 THEN RAISE; END IF;
END;
/

BEGIN
  EXECUTE IMMEDIATE '
    CREATE TABLE ROLES (
      id   NUMBER PRIMARY KEY,
      name VARCHAR2(50) UNIQUE NOT NULL
    )';
EXCEPTION WHEN OTHERS THEN IF SQLCODE != -955 THEN RAISE; END IF;
END;
/

BEGIN
  EXECUTE IMMEDIATE '
    CREATE TABLE USUARIOS_ROLES (
      user_id NUMBER NOT NULL,
      role_id NUMBER NOT NULL,
      PRIMARY KEY (user_id, role_id),
      FOREIGN KEY (user_id) REFERENCES USUARIOS(id),
      FOREIGN KEY (role_id) REFERENCES ROLES(id)
    )';
EXCEPTION WHEN OTHERS THEN IF SQLCODE != -955 THEN RAISE; END IF;
END;
/

-- Perfis
MERGE INTO ROLES t USING (SELECT 1 id, 'ROLE_ADMIN' name FROM dual) s ON (t.id = s.id)
WHEN NOT MATCHED THEN INSERT (id,name) VALUES (s.id,s.name);

MERGE INTO ROLES t USING (SELECT 2 id, 'ROLE_USER' name FROM dual) s ON (t.id = s.id)
WHEN NOT MATCHED THEN INSERT (id,name) VALUES (s.id,s.name);

-- Usuários (senha '123456' em bcrypt)
MERGE INTO USUARIOS t
USING (SELECT 1 id, 'admin@mottu.com' username,
              '$2a$10$wJxZ3PcfqJ8Zf7x5D8Rr8u8t1m9wWD5mM2nH5v8QH8UVm4c8H4sfi' password,
              1 enabled FROM dual) s
ON (t.id = s.id)
WHEN NOT MATCHED THEN INSERT (id,username,password,enabled) VALUES (s.id,s.username,s.password,s.enabled);

MERGE INTO USUARIOS t
USING (SELECT 2 id, 'user@mottu.com'  username,
              '$2a$10$wJxZ3PcfqJ8Zf7x5D8Rr8u8t1m9wWD5mM2nH5v8QH8UVm4c8H4sfi' password,
              1 enabled FROM dual) s
ON (t.id = s.id)
WHEN NOT MATCHED THEN INSERT (id,username,password,enabled) VALUES (s.id,s.username,s.password,s.enabled);

-- Vínculos
MERGE INTO USUARIOS_ROLES t USING (SELECT 1 user_id, 1 role_id FROM dual) s ON (t.user_id=s.user_id AND t.role_id=s.role_id)
WHEN NOT MATCHED THEN INSERT (user_id, role_id) VALUES (s.user_id, s.role_id);

MERGE INTO USUARIOS_ROLES t USING (SELECT 2 user_id, 2 role_id FROM dual) s ON (t.user_id=s.user_id AND t.role_id=s.role_id)
WHEN NOT MATCHED THEN INSERT (user_id, role_id) VALUES (s.user_id, s.role_id);
