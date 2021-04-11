CREATE TABLE IF NOT EXISTS Citizenship (
    id                   INTEGER                            COMMENT 'Unique identifier' PRIMARY KEY AUTO_INCREMENT,
    version              INTEGER       NOT NULL             COMMENT 'Hibernate internal service field',
    code                 VARCHAR(3)    NOT NULL             COMMENT 'Citizenship code',
    citizenship_name     VARCHAR(50)   NOT NULL             COMMENT 'Citizenship name'
);
COMMENT ON TABLE Citizenship IS 'directory of citizenship';

CREATE TABLE IF NOT EXISTS Document_type (
    id             INTEGER                      COMMENT 'Unique identifier' PRIMARY KEY AUTO_INCREMENT,
    version        INTEGER       NOT NULL       COMMENT 'Hibernate internal service field',
    code           VARCHAR(3)    NOT NULL       COMMENT 'Document code',
    document_name  VARCHAR(50)   NOT NULL       COMMENT 'Document name'
);
COMMENT ON TABLE Document_type IS 'directory of document types';

CREATE TABLE IF NOT EXISTS Document (
     user_id         INTEGER                     COMMENT 'Unique identifier' PRIMARY KEY,
     version         INTEGER     NOT NULL        COMMENT 'Hibernate internal service field',
     doc_type_id     INTEGER     NOT NULL        COMMENT 'Real document',
     document_date   DATE        NOT NULL        COMMENT 'Real document date',
     document_number VARCHAR(10) NOT NULL        COMMENT 'Real document number'
);
COMMENT ON TABLE Document IS 'real documents table';
ALTER TABLE Document ADD FOREIGN KEY (doc_type_id) REFERENCES Document_type(id);
CREATE INDEX IX_User_document_type_id ON Document (doc_type_id);

CREATE TABLE IF NOT EXISTS Organization (
    id         INTEGER                         COMMENT 'Unique identifier' PRIMARY KEY AUTO_INCREMENT,
    version    INTEGER       NOT NULL          COMMENT 'Hibernate internal service field',
    short_name VARCHAR(50)   NOT NULL          COMMENT 'Short name of an organization',
    full_name  VARCHAR(150)  NOT NULL          COMMENT 'Full name of an organization',
    inn        VARCHAR(10)   NOT NULL          COMMENT 'Organization TIN',
    kpp        VARCHAR(9)    NOT NULL          COMMENT 'Organization RRC',
    address    VARCHAR(255)  NOT NULL          COMMENT 'Head office organization address',
    phone      VARCHAR(10)                     COMMENT 'Head office organization phone',
    is_active  BOOLEAN       DEFAULT TRUE      COMMENT 'Organization status'
);
CREATE INDEX IX_Organization_name ON Organization (short_name);

CREATE TABLE IF NOT EXISTS Office (
    id              INTEGER                    COMMENT 'Unique identifier' PRIMARY KEY AUTO_INCREMENT,
    version         INTEGER       NOT NULL     COMMENT 'Hibernate internal service field',
    office_name     VARCHAR(50)   NOT NULL     COMMENT 'Short name of an organization office',
    address         VARCHAR(255)  NOT NULL     COMMENT 'Address of an organization office',
    phone           VARCHAR(10)                COMMENT 'Phone number of an organization office',
    is_active       BOOLEAN       DEFAULT TRUE COMMENT 'Status of an organization office ',
    organisation_id INTEGER       NOT NULL     COMMENT 'Unique identifier of an organization to which an office belongs'
);
ALTER TABLE Office ADD FOREIGN KEY (organisation_id) REFERENCES Organization(id);
CREATE INDEX IX_Office_id_organisation ON Office (organisation_id);

CREATE TABLE IF NOT EXISTS User (
    id               INTEGER                    COMMENT 'Unique identifier' PRIMARY KEY AUTO_INCREMENT,
    version          INTEGER       NOT NULL     COMMENT 'Hibernate internal service field',
    first_name       VARCHAR(50)   NOT NULL     COMMENT 'User name',
    second_name      VARCHAR(50)                COMMENT 'User surname',
    middle_name      VARCHAR(50)                COMMENT 'User middle name',
    occupation       VARCHAR(50)   NOT NULL     COMMENT 'User position',
    phone            VARCHAR(10)                COMMENT 'User phone',
    is_identified    BOOLEAN       DEFAULT TRUE COMMENT 'Is user identified?',
    citizenship_id   INTEGER       NOT NULL     COMMENT 'User citizenship',
    office_id        INTEGER       NOT NULL     COMMENT 'Unique identifier of an organization to which a user belongs to'
);

ALTER TABLE User ADD FOREIGN KEY (office_id) REFERENCES Office(id);
ALTER TABLE User ADD FOREIGN KEY (citizenship_id)  REFERENCES Citizenship(id);
CREATE INDEX IX_User_id_citizenship ON User (citizenship_id);
CREATE INDEX IX_User_first_name ON User (first_name);
CREATE INDEX IX_User_id_office ON User (office_id);

ALTER TABLE Document ADD FOREIGN KEY (user_id)     REFERENCES User(id);
CREATE INDEX IX_User_id on DOCUMENT (user_id);