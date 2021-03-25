INSERT INTO Citizenship (code, citizenship_name)
VALUES ('001', 'Russian federation'),
       ('002', 'Ukraine'),
       ('003', 'USA'),
       ('004', 'UK');

INSERT INTO Document_type (code, document_name)
VALUES ('001', 'Passport'),
       ('002', 'Driver license');

INSERT INTO Document (doc_type_id, citizenship_id, document_date, document_number)
VALUES (1, 1, '2007-01-01', '778-887'),
       (1, 2, '2009-01-02', '748-447'),
       (2, 3, '2014-02-03', '798-887'),
       (1, 4, '2013-04-03', '987-876'),
       (2, 1, '2020-01-05', '984-998');

INSERT INTO Address (city, street, house)
VALUES ('Moscow', 'Proletarsky district', '20'),
       ('Moscow', 'Kievskiy district', '5'),
       ('S.Petersburg', 'River district', '4'),
       ('S.Petersburg', 'Field district', '2'),
       ('London', 'Green street', '202'),
       ('London', 'Biker street', '24'),
       ('Boston', 'Black street', '65');

INSERT INTO Organization (short_name, full_name, inn, kpp, address_id, phone, is_active)
VALUES ('Firstbank', 'LLC_First_bank ', '1234567890', '987654321', 1, '9897785432', true),
       ('Secondbank', 'LLC_Second_bank ', '0987654321', '123456789', 2, '9897785432', true);

INSERT INTO Office (office_name, address_id, phone, isActive, id_organisation)
VALUES ('Head_office', 3, '4959999958', true, 1),
       ('Just_office', 4, '4959999957', true, 1),
       ('One_more_office', 5, '369988957', true, 1),
       ('Head_office', 6, '369879954', true, 2),
       ('Just_office', 7, '44488996', true, 2);

INSERT INTO User (first_name, second_name, middle_name, position, phone, document_id, is_identified, id_office)
VALUES ('Alexander', 'Vinogradov', 'Dmitrievich', 'CIO', '9999453378', 1, true, 1),
       ('Vladimir', 'Vinogradov', 'Dmitrievich', 'CTO', '9864563221', 1, false, 2),
       ('Alexey', 'Lobanov', 'Ivanovich', 'COO', '9997876532', 2, true, 3),
       ('Max', 'Smith', 'Black', 'accountant', '88898965', 1, false, 4),
       ('John', 'Doe', 'J', 'unknown', '00000000', 1, false, 1);



