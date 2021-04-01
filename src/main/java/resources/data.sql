INSERT INTO Citizenship (code, citizenship_name)
VALUES ('001', 'Russian federation'),
       ('002', 'Ukraine'),
       ('003', 'USA'),
       ('004', 'UK');

INSERT INTO Document_type (code, document_name)
VALUES ('001', 'Passport'),
       ('002', 'Driver license');

INSERT INTO Document (id_doc_type, document_date, document_number)
VALUES (1, '2007-01-01', '778-887'),
       (1, '2009-01-02', '748-447'),
       (2, '2014-02-03', '798-887'),
       (1, '2013-04-03', '987-876'),
       (2, '2020-01-05', '984-998');


INSERT INTO Organization (short_name, full_name, inn, kpp, address, phone, is_active)
VALUES ('Firstbank', 'LLC_First_bank ', '1234567890', '987654321', 'Moscow, Black street, 21', '9897785432', true),
       ('Secondbank', 'LLC_Second_bank ', '0987654321', '123456789', 'Boston, Green street, 4', '9897785432', true);

INSERT INTO Office (office_name, address, phone, isActive, id_organisation)
VALUES ('Head_office', 'Boston, Blue street, 5', '4959999958', true, 1),
       ('Just_office', 'Paris, Just street, 2', '4959999957', true, 1),
       ('One_more_office', 'Berlin, Aleksandr platz, 5', '369988957', true, 1),
       ('Head_office', 'Moscow, Red square, 1', '369879954', true, 2),
       ('Just_office', 'New-York, Yellow street, 2', '44488996', true, 2);

INSERT INTO User (first_name, second_name, middle_name, occupation, phone, document_id, is_identified, citizenship_id,  office_id)
VALUES ('Alexander', 'Vinogradov', 'Dmitrievich', 'CIO', '9999453378', 1, true,1, 1),
       ('Vladimir', 'Vinogradov', 'Dmitrievich', 'CTO', '9864563221', 1, false,2, 2),
       ('Alexey', 'Lobanov', 'Ivanovich', 'COO', '9997876532', 2, true,3, 3),
       ('Max', 'Smith', 'Black', 'accountant', '88898965', 1, false,4, 4),
       ('John', 'Doe', 'J', 'unknown', '00000000', 1, false,1, 1);



