INSERT INTO Citizenship (version, code, citizenship_name)
VALUES (0, '001', 'Russian federation'),
       (0, '002', 'Ukraine'),
       (0, '003', 'USA'),
       (0, '004', 'UK');

INSERT INTO Document_type (version, code, document_name)
VALUES (0, '001', 'Passport'),
       (0, '002', 'Driver license');

INSERT INTO Organization (version, short_name, full_name, inn, kpp, address, phone, is_active)
VALUES (0, 'Firstbank', 'LLC_First_bank ', '1234567890', '987654321', 'Moscow, Black street, 21', '9897785432', true),
       (0, 'Secondbank', 'LLC_Second_bank ', '0987654321', '123456789', 'Boston, Green street, 4', '9897785432', true);

INSERT INTO Office (version, office_name, address, phone, IS_ACTIVE, organisation_id)
VALUES (0, 'Head_office', 'Boston, Blue street, 5', '4959999958', true, 1),
       (0, 'Just_office', 'Paris, Just street, 2', '4959999957', true, 1),
       (0, 'One_more_office', 'Berlin, Aleksandr platz, 5', '369988957', true, 1),
       (0, 'Head_office', 'Moscow, Red square, 1', '369879954', true, 2),
       (0, 'Just_office', 'New-York, Yellow street, 2', '44488996', true, 2);

INSERT INTO User (version, first_name, second_name, middle_name, occupation, phone, is_identified, citizenship_id,  office_id)
VALUES (0, 'Alexander', 'Vinogradov', 'Dmitrievich', 'CIO', '9999453378', true,1, 1),
       (0, 'Vladimir', 'Vinogradov', 'Dmitrievich', 'CTO', '9864563221', false,2, 2),
       (0, 'Alexey', 'Lobanov', 'Ivanovich', 'COO', '9997876532', true,3, 3),
       (0, 'Max', 'Smith', 'Black', 'accountant', '88898965', false,4, 4),
       (0, 'John', 'Doe', 'J', 'unknown', '00000000', false,1, 1);

INSERT INTO Document (version, user_id, doc_type_id, document_date, document_number)
VALUES (0, 1, 1, '2007-01-01', '778-887'),
       (0, 2, 1, '2009-01-02', '748-447'),
       (0, 3, 2, '2014-02-03', '798-887'),
       (0, 4, 1, '2013-04-03', '987-876'),
       (0, 5, 2, '2020-01-05', '984-998');

