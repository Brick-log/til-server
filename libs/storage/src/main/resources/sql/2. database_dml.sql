insert into category(identifier, name) VALUE ('0ec30e71-38ba-4837-8804-f0e1180c5bf1', 'category1');
insert into category(identifier, name) VALUE ('0ec30e71-38ba-4837-8804-f0e1180c5bf2', 'category2');
insert into category(identifier, name) VALUE ('0ec30e71-38ba-4837-8804-f0e1180c5bf3', 'category3');
insert into category(identifier, name) VALUE ('0ec30e71-38ba-4837-8804-f0e1180c5bf4', 'category4');
insert into category(identifier, name) VALUE ('0ec30e71-38ba-4837-8804-f0e1180c5bf5', 'category5');

insert into user(name, user_identifier, category_identifier, introduction, thumbnail_url, path, status) VALUE (
                                                                                                                 'duck test',
                                                                                                                 '11111111-1111-4837-8804-f0e1180c5bf5',
                                                                                                                 '0ec30e71-38ba-4837-8804-f0e1180c5bf1',
                                                                                                                 'hi',
                                                                                                                 'https://www.naver.com',
                                                                                                                 'duck',
                                                                                                                 'ON_BOARDING'
    );

insert into post(identifier, category_identifier, user_identifier, title, description, url, hit_count,
                 created_at) VALUE (
                                    '22222222-38ba-4837-8804-f0e1180c5bf1',
                                    '0ec30e71-38ba-4837-8804-f0e1180c5bf1',
                                    '11111111-1111-4837-8804-f0e1180c5bf5',
                                    'title',
                                    'descrption',
                                    'https://www.naver.com',
                                    '0',
                                    now()
    );
insert into post(identifier, category_identifier, user_identifier, title, description, url, hit_count,
                 created_at) VALUE (
                                    '22222222-38ba-4837-8804-f0e1180c5bf2',
                                    '0ec30e71-38ba-4837-8804-f0e1180c5bf1',
                                    '11111111-1111-4837-8804-f0e1180c5bf5',
                                    'title',
                                    'descrption',
                                    'https://www.naver.com',
                                    '0',
                                    now()
    );
insert into post(identifier, category_identifier, user_identifier, title, description, url, hit_count,
                 created_at) VALUE (
                                    '22222222-38ba-4837-8804-f0e1180c5bf3',
                                    '0ec30e71-38ba-4837-8804-f0e1180c5bf1',
                                    '11111111-1111-4837-8804-f0e1180c5bf5',
                                    'title',
                                    'descrption',
                                    'https://www.naver.com',
                                    '0',
                                    now()
    );
insert into post(identifier, category_identifier, user_identifier, title, description, url, hit_count,
                 created_at) VALUE (
                                    '22222222-38ba-4837-8804-f0e1180c5bf4',
                                    '0ec30e71-38ba-4837-8804-f0e1180c5bf1',
                                    '11111111-1111-4837-8804-f0e1180c5bf5',
                                    'title',
                                    'descrption',
                                    'https://www.naver.com',
                                    '0',
                                    now()
    );
insert into post(identifier, category_identifier, user_identifier, title, description, url, hit_count,
                 created_at) VALUE (
                                    '22222222-38ba-4837-8804-f0e1180c5bf5',
                                    '0ec30e71-38ba-4837-8804-f0e1180c5bf1',
                                    '11111111-1111-4837-8804-f0e1180c5bf5',
                                    'title',
                                    'descrption',
                                    'https://www.naver.com',
                                    '0',
                                    now()
    );
insert into post(identifier, category_identifier, user_identifier, title, description, url, hit_count,
                 created_at) VALUE (
                                    '22222222-38ba-4837-8804-f0e1180c5bf6',
                                    '0ec30e71-38ba-4837-8804-f0e1180c5bf1',
                                    '11111111-1111-4837-8804-f0e1180c5bf5',
                                    'title',
                                    'descrption',
                                    'https://www.naver.com',
                                    '0',
                                    now()
    );
