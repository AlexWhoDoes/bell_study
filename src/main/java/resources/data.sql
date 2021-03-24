INSERT INTO Citizenship (code, citizenship_name)
VALUES ('001', 'Russian federation'),
       ('002', 'Ukraine'),
       ('003', 'USA'),
       ('004', 'UK');

INSERT INTO Document_type (code, document_name)
VALUES ('001', 'Passport'),
       ('002', 'Driver license');

INSERT INTO Document (document, citizenship, document_date)
VALUES (1, 1, '2007-01-01'),
       (1, 2, '2009-01-02'),
       (2, 3, '2014-02-03'),
       (1, 4, '2013-04-03'),
       (2, 1, '2020-01-05');

INSERT INTO Organization (short_name, full_name, inn, kpp, address, phone, is_active)
VALUES ('Firstbank', 'LLC_First_bank ', '1234567890', '987654321', 'S.Petersburg, Green street, 54', '9897785432', true),
       ('Secondbank', 'LLC_Second_bank ', '0987654321', '123456789', 'Moscow, Black street, 1', '9897785432', true);

INSERT INTO Office (office_name, address, phone, isActive, id_organisation)
VALUES ('Head_office', 'Moscow, Proletarsky district, 2-1-8', '4959999958', true, 1),
       ('Just_office', 'Moscow, Kievskiy district, 7', '4959999957', true, 1),
       ('One_more_office', 'S.Petersburg, River district, 24', '369988957', true, 1),
       ('Head_office', 'S.Petersburg, Field district, 44-55', '369879954', true, 2),
       ('Just_office', 'London, Biker street, 44-55', '44488996', true, 2);

INSERT INTO User (first_name, second_name, middle_name, position, phone, document, is_identified, id_office)
VALUES ('Alexander', 'Vinogradov', 'Dmitrievich', 'CIO', '9999453378', 1, true, 1),
       ('Vladimir', 'Vinogradov', 'Dmitrievich', 'CTO', '9864563221', 1, false, 2),
       ('Alexey', 'Lobanov', 'Ivanovich', 'COO', '9997876532', 2, true, 3),
       ('Max', 'Smith', 'Black', 'accountant', '88898965', 1, false, 4),
       ('John', 'Doe', 'J', 'unknown', '00000000', 1, false, 1);



