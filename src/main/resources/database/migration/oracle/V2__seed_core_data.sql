-- ===== V2: Seed mínimo (idempotente, Oracle correto) =====
-- Regra: SEM lista de colunas após o alias do subselect no USING.
-- Use aliases dentro do SELECT e termine cada MERGE com ponto-e-vírgula.

-- =======================
-- MODELOS (IDs 1..5)
-- =======================
MERGE INTO T_CM_MODELO t
USING (SELECT 1 id_modelo, 'Honda CG 160' nm_modelo FROM dual) s
ON (t.id_modelo = s.id_modelo)
WHEN NOT MATCHED THEN
  INSERT (id_modelo, nm_modelo) VALUES (s.id_modelo, s.nm_modelo);

MERGE INTO T_CM_MODELO t
USING (SELECT 2 id_modelo, 'Yamaha Factor 150' nm_modelo FROM dual) s
ON (t.id_modelo = s.id_modelo)
WHEN NOT MATCHED THEN
  INSERT (id_modelo, nm_modelo) VALUES (s.id_modelo, s.nm_modelo);

MERGE INTO T_CM_MODELO t
USING (SELECT 3 id_modelo, 'Honda CB 500' nm_modelo FROM dual) s
ON (t.id_modelo = s.id_modelo)
WHEN NOT MATCHED THEN
  INSERT (id_modelo, nm_modelo) VALUES (s.id_modelo, s.nm_modelo);

MERGE INTO T_CM_MODELO t
USING (SELECT 4 id_modelo, 'Yamaha MT-07' nm_modelo FROM dual) s
ON (t.id_modelo = s.id_modelo)
WHEN NOT MATCHED THEN
  INSERT (id_modelo, nm_modelo) VALUES (s.id_modelo, s.nm_modelo);

MERGE INTO T_CM_MODELO t
USING (SELECT 5 id_modelo, 'Honda Biz 125' nm_modelo FROM dual) s
ON (t.id_modelo = s.id_modelo)
WHEN NOT MATCHED THEN
  INSERT (id_modelo, nm_modelo) VALUES (s.id_modelo, s.nm_modelo);

-- =======================
-- FILIAIS (IDs 1..5)
-- =======================
MERGE INTO T_CM_FILIAL_DEPARTAMENTO t
USING (SELECT 1 id_filial_departamento, 'Filial São Paulo' nm_filial_departamento FROM dual) s
ON (t.id_filial_departamento = s.id_filial_departamento)
WHEN NOT MATCHED THEN
  INSERT (id_filial_departamento, nm_filial_departamento)
  VALUES (s.id_filial_departamento, s.nm_filial_departamento);

MERGE INTO T_CM_FILIAL_DEPARTAMENTO t
USING (SELECT 2 id_filial_departamento, 'Filial Rio de Janeiro' nm_filial_departamento FROM dual) s
ON (t.id_filial_departamento = s.id_filial_departamento)
WHEN NOT MATCHED THEN
  INSERT (id_filial_departamento, nm_filial_departamento)
  VALUES (s.id_filial_departamento, s.nm_filial_departamento);

MERGE INTO T_CM_FILIAL_DEPARTAMENTO t
USING (SELECT 3 id_filial_departamento, 'Filial Campinas' nm_filial_departamento FROM dual) s
ON (t.id_filial_departamento = s.id_filial_departamento)
WHEN NOT MATCHED THEN
  INSERT (id_filial_departamento, nm_filial_departamento)
  VALUES (s.id_filial_departamento, s.nm_filial_departamento);

MERGE INTO T_CM_FILIAL_DEPARTAMENTO t
USING (SELECT 4 id_filial_departamento, 'Filial Belo Horizonte' nm_filial_departamento FROM dual) s
ON (t.id_filial_departamento = s.id_filial_departamento)
WHEN NOT MATCHED THEN
  INSERT (id_filial_departamento, nm_filial_departamento)
  VALUES (s.id_filial_departamento, s.nm_filial_departamento);

MERGE INTO T_CM_FILIAL_DEPARTAMENTO t
USING (SELECT 5 id_filial_departamento, 'Filial Curitiba' nm_filial_departamento FROM dual) s
ON (t.id_filial_departamento = s.id_filial_departamento)
WHEN NOT MATCHED THEN
  INSERT (id_filial_departamento, nm_filial_departamento)
  VALUES (s.id_filial_departamento, s.nm_filial_departamento);

-- =======================
-- CLIENTES (IDs 1..5)
-- =======================
MERGE INTO T_CM_CLIENTE t
USING (SELECT 1 id_cliente, 1 id_logradouro, 'Carlos Silva' nm_cliente,
              '123.456.789-00' nr_cpf, 'carlos@mottu.com' nm_email FROM dual) s
ON (t.id_cliente = s.id_cliente)
WHEN NOT MATCHED THEN
  INSERT (id_cliente, id_logradouro, nm_cliente, nr_cpf, nm_email)
  VALUES (s.id_cliente, s.id_logradouro, s.nm_cliente, s.nr_cpf, s.nm_email);

MERGE INTO T_CM_CLIENTE t
USING (SELECT 2 id_cliente, 2 id_logradouro, 'Ana Costa' nm_cliente,
              '987.654.321-00' nr_cpf, 'ana@mottu.com' nm_email FROM dual) s
ON (t.id_cliente = s.id_cliente)
WHEN NOT MATCHED THEN
  INSERT (id_cliente, id_logradouro, nm_cliente, nr_cpf, nm_email)
  VALUES (s.id_cliente, s.id_logradouro, s.nm_cliente, s.nr_cpf, s.nm_email);

MERGE INTO T_CM_CLIENTE t
USING (SELECT 3 id_cliente, 3 id_logradouro, 'João Pereira' nm_cliente,
              '111.222.333-44' nr_cpf, 'joao@mottu.com' nm_email FROM dual) s
ON (t.id_cliente = s.id_cliente)
WHEN NOT MATCHED THEN
  INSERT (id_cliente, id_logradouro, nm_cliente, nr_cpf, nm_email)
  VALUES (s.id_cliente, s.id_logradouro, s.nm_cliente, s.nr_cpf, s.nm_email);

MERGE INTO T_CM_CLIENTE t
USING (SELECT 4 id_cliente, 4 id_logradouro, 'Mariana Alves' nm_cliente,
              '555.666.777-88' nr_cpf, 'mariana@mottu.com' nm_email FROM dual) s
ON (t.id_cliente = s.id_cliente)
WHEN NOT MATCHED THEN
  INSERT (id_cliente, id_logradouro, nm_cliente, nr_cpf, nm_email)
  VALUES (s.id_cliente, s.id_logradouro, s.nm_cliente, s.nr_cpf, s.nm_email);

MERGE INTO T_CM_CLIENTE t
USING (SELECT 5 id_cliente, 5 id_logradouro, 'Ricardo Gomes' nm_cliente,
              '999.000.111-22' nr_cpf, 'ricardo@mottu.com' nm_email FROM dual) s
ON (t.id_cliente = s.id_cliente)
WHEN NOT MATCHED THEN
  INSERT (id_cliente, id_logradouro, nm_cliente, nr_cpf, nm_email)
  VALUES (s.id_cliente, s.id_logradouro, s.nm_cliente, s.nr_cpf, s.nm_email);

-- =======================
-- MOTOS (IDs 1..5)
-- =======================
MERGE INTO T_CM_MOTO t
USING (SELECT 1 id_moto, 1 id_modelo, 1 id_filial, 'ABC-1234' nm_placa,
              'Disponivel' st_moto, 15000 km_rodado FROM dual) s
ON (t.id_moto = s.id_moto)
WHEN NOT MATCHED THEN
  INSERT (id_moto, id_modelo, id_filial_departamento, nm_placa, st_moto, km_rodado)
  VALUES (s.id_moto, s.id_modelo, s.id_filial, s.nm_placa, s.st_moto, s.km_rodado);

MERGE INTO T_CM_MOTO t
USING (SELECT 2 id_moto, 2 id_modelo, 2 id_filial, 'XYZ-5678' nm_placa,
              'Em manutencao' st_moto, 8000 km_rodado FROM dual) s
ON (t.id_moto = s.id_moto)
WHEN NOT MATCHED THEN
  INSERT (id_moto, id_modelo, id_filial_departamento, nm_placa, st_moto, km_rodado)
  VALUES (s.id_moto, s.id_modelo, s.id_filial, s.nm_placa, s.st_moto, s.km_rodado);

MERGE INTO T_CM_MOTO t
USING (SELECT 3 id_moto, 3 id_modelo, 3 id_filial, 'DEF-4321' nm_placa,
              'Disponivel' st_moto, 12000 km_rodado FROM dual) s
ON (t.id_moto = s.id_moto)
WHEN NOT MATCHED THEN
  INSERT (id_moto, id_modelo, id_filial_departamento, nm_placa, st_moto, km_rodado)
  VALUES (s.id_moto, s.id_modelo, s.id_filial, s.nm_placa, s.st_moto, s.km_rodado);

MERGE INTO T_CM_MOTO t
USING (SELECT 4 id_moto, 4 id_modelo, 4 id_filial, 'GHI-8765' nm_placa,
              'Em manutencao' st_moto, 5000 km_rodado FROM dual) s
ON (t.id_moto = s.id_moto)
WHEN NOT MATCHED THEN
  INSERT (id_moto, id_modelo, id_filial_departamento, nm_placa, st_moto, km_rodado)
  VALUES (s.id_moto, s.id_modelo, s.id_filial, s.nm_placa, s.st_moto, s.km_rodado);

MERGE INTO T_CM_MOTO t
USING (SELECT 5 id_moto, 5 id_modelo, 5 id_filial, 'JKL-1357' nm_placa,
              'Disponivel' st_moto, 7000 km_rodado FROM dual) s
ON (t.id_moto = s.id_moto)
WHEN NOT MATCHED THEN
  INSERT (id_moto, id_modelo, id_filial_departamento, nm_placa, st_moto, km_rodado)
  VALUES (s.id_moto, s.id_modelo, s.id_filial, s.nm_placa, s.st_moto, s.km_rodado);

-- =======================
-- ALUGUÉIS (IDs 1..5)
-- =======================
MERGE INTO T_CM_ALUGUEL t
USING (SELECT 1 id_aluguel, 1 id_cliente, 1 id_moto,
              SYSTIMESTAMP - 7 dt_retirada, CAST(NULL AS TIMESTAMP) dt_devolucao FROM dual) s
ON (t.id_aluguel = s.id_aluguel)
WHEN NOT MATCHED THEN
  INSERT (id_aluguel, id_cliente, id_moto, dt_retirada, dt_devolucao)
  VALUES (s.id_aluguel, s.id_cliente, s.id_moto, s.dt_retirada, s.dt_devolucao);

MERGE INTO T_CM_ALUGUEL t
USING (SELECT 2 id_aluguel, 2 id_cliente, 2 id_moto,
              SYSTIMESTAMP - 10 dt_retirada, SYSTIMESTAMP - 8 dt_devolucao FROM dual) s
ON (t.id_aluguel = s.id_aluguel)
WHEN NOT MATCHED THEN
  INSERT (id_aluguel, id_cliente, id_moto, dt_retirada, dt_devolucao)
  VALUES (s.id_aluguel, s.id_cliente, s.id_moto, s.dt_retirada, s.dt_devolucao);

MERGE INTO T_CM_ALUGUEL t
USING (SELECT 3 id_aluguel, 3 id_cliente, 3 id_moto,
              SYSTIMESTAMP - 5 dt_retirada, CAST(NULL AS TIMESTAMP) dt_devolucao FROM dual) s
ON (t.id_aluguel = s.id_aluguel)
WHEN NOT MATCHED THEN
  INSERT (id_aluguel, id_cliente, id_moto, dt_retirada, dt_devolucao)
  VALUES (s.id_aluguel, s.id_cliente, s.id_moto, s.dt_retirada, s.dt_devolucao);

MERGE INTO T_CM_ALUGUEL t
USING (SELECT 4 id_aluguel, 4 id_cliente, 4 id_moto,
              SYSTIMESTAMP - 12 dt_retirada, SYSTIMESTAMP - 10 dt_devolucao FROM dual) s
ON (t.id_aluguel = s.id_aluguel)
WHEN NOT MATCHED THEN
  INSERT (id_aluguel, id_cliente, id_moto, dt_retirada, dt_devolucao)
  VALUES (s.id_aluguel, s.id_cliente, s.id_moto, s.dt_retirada, s.dt_devolucao);

MERGE INTO T_CM_ALUGUEL t
USING (SELECT 5 id_aluguel, 5 id_cliente, 5 id_moto,
              SYSTIMESTAMP - 1 dt_retirada, CAST(NULL AS TIMESTAMP) dt_devolucao FROM dual) s
ON (t.id_aluguel = s.id_aluguel)
WHEN NOT MATCHED THEN
  INSERT (id_aluguel, id_cliente, id_moto, dt_retirada, dt_devolucao)
  VALUES (s.id_aluguel, s.id_cliente, s.id_moto, s.dt_retirada, s.dt_devolucao);
