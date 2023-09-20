INSERT INTO vacationers (id, first_name, second_name, middle_name, birth_date, phone, email, type, is_settled, is_evicted, is_deleted)
VALUES (nextval('vacationers_sequence'), 'Иван', 'Кочарыгин', 'Сергеевич', '1997-11-15', '89046574433',
        'Ivan.k@mail.ru', 'OWNER', false, false, false),
       (nextval('vacationers_sequence'), 'Сергей', 'Любин', 'Дмитриевич', '1981-02-14', '89057658899',
        'Sergey.l81@mail.ru', 'OWNER', false, false, false),
       (nextval('vacationers_sequence'), 'Марина', 'Николаева', 'Ивановна',  '1975-06-25', '89036579923',
        'nikolaeva.m@mail.ru', 'GUEST', false, false, false),
       (nextval('vacationers_sequence'), 'Ольга', 'Бабушкина', 'Васильевна', '1983-12-28', '89056663474',
        'Babushka.o83@mail.ru', 'GUEST', false, false, false),
       (nextval('vacationers_sequence'), 'Никита', 'Полежаев', 'Андреевич', '1992-03-13', '89067432345',
        'Nikita.pol@mail.ru', 'OWNER', false, false, false),
       (nextval('vacationers_sequence'), 'Владимир', 'Афанасьев', 'Александрович', '1969-07-02', '89012346573',
        'Vlad.afa@mail.ru', 'GUEST', false, false, false),
       (nextval('vacationers_sequence'), 'Наталья', 'Герасимова', 'Ивановна', '1976-06-13', '89057748596',
        'gerasimova.n76@mail.ru', 'OWNER', false, false, false),
       (nextval('vacationers_sequence'), 'Елизавета', 'Началова', 'Владимировна', '1985-05-19', '89027774562',
        'nachalova85@mail.ru', 'GUEST', false, false, false),
       (nextval('vacationers_sequence'), 'Александр', 'Мухин', 'Алексеевич', '1988-01-22', '83333333333',
        'aleksandr8888@mail.ru', 'OWNER', false, false, false),
       (nextval('vacationers_sequence'), 'Юлия', 'Соболева', 'Сергеевна', '1991-03-05', '89028839045',
        'soboleva.you@mail.ru', 'OWNER', false, false, false),
       (nextval('vacationers_sequence'), 'Иван', 'Иванов', 'Никитович', '1978-08-24', '89067782399',
        'Ivan.i@mail.ru', 'GUEST', false, false, false);

insert into rooms (id, is_deleted, description, title, amount, capacity, cost_per_night)
values (nextval('rooms_sequence'), false, 'Стандартный номер для двух гостей с возможностью размещения ребенка или ' ||
                                          'взрослого на дополнительном месте. Эта категория номеров очень популярна ' ||
                                          'среди наших гостей.', 'Стандартный', '30', 3, 12000),
    (nextval('rooms_sequence'), false, 'Номера «Люкс» ждут гостей, которые ценят индивидуальный сервис, внимание и ' ||
                                       'высокое качество обслуживания, питание в VIP-зале ресторана. Номер повышенной ' ||
                                       'комфортности для двух гостей состоит из гостиной и спальни. Предусмотрено ' ||
                                       'дополнительное место для размещения ребенка/взрослого.',
     'Люкс', '15', 4, 21500);


insert into treatments (id, is_deleted, description, title, duration)
values (nextval('treatments_sequence'), false, 'Классическая санаторно-курортная путевка с расширенным набором ' ||
                                               'лечебно-диагностических услуг рекомендована при различных ' ||
                                               'заболеваниях органов пищеварения, обмена веществ, синдрома хронической ' ||
                                               'усталости и др.', 'Источник-здоровье', 5),
       (nextval('treatments_sequence'), false, 'Цель и задачи: улучшение обмена веществ; снятие физического и ' ||
                                               'эмоционального стресса; достижение тенденции к улучшению функции ' ||
                                               'суставов; уменьшение болевого синдрома; улучшение качества жизни.',
                                                'Источник-здоровье суставов', 10),
       (nextval('treatments_sequence'), false, 'Цель и задачи: лечение заболеваний вегетативной нервной системы, ' ||
                                               'восстановление хорошей переносимости физических нагрузок, стимуляция ' ||
                                               'адаптационно-компенсаторных механизмов, нормализация сна, оптимизацию ' ||
                                               'работы всех органов и систем, повышение качества жизни.',
                                                'Источник-антистресс', 10),
       (nextval('treatments_sequence'), false, 'Цель и задачи: профилактика обострений хронической ишемической болезни ' ||
                                               'сердца, стабилизация артериального давления, улучшение работы сердца и ' ||
                                               'сосудистого тонуса, восстановление хорошей переносимости физических ' ||
                                               'нагрузок, оптимизацию работы всех органов и систем, формирование ' ||
                                               'правильного отношение к питанию, повышение качества жизни.',
                                                'Источник-здоровье сердца', 12);



insert into booking_info (id, is_deleted, created_when, start_date, end_date, is_treatment_included, room_id, treatment_id, vacationer_id)
values (nextval('booking_info_sequence'), false, '2023-07-02 01:27:04.044067', '2023-07-10', '2023-07-15', false, 1, null, 1),
       (nextval('booking_info_sequence'), false, '2023-06-10 01:27:04.044067', '2023-09-12', '2023-09-24', true, 1, 1, 1),
       (nextval('booking_info_sequence'), false, '2023-02-12 01:27:04.044067', '2023-08-13', '2023-08-24', true, 2, 2, 2),
       (nextval('booking_info_sequence'), false, '2023-03-03 01:27:04.044067', '2023-10-21', '2023-10-30', false, 1, null, 3),
       (nextval('booking_info_sequence'), false, '2023-01-26 01:27:04.044067', '2024-02-21', '2024-03-06', true, 1, 4, 3),
       (nextval('booking_info_sequence'), false, '2023-04-07 01:27:04.044067', '2023-11-02', '2023-11-09', false, 1, null, 4),
       (nextval('booking_info_sequence'), false, '2023-05-25 01:27:04.044067', '2023-11-01', '2023-11-10', false, 2, null, 5),
       (nextval('booking_info_sequence'), false, '2023-04-16 01:27:04.044067', '2024-01-05', '2024-01-16', false, 1, null, 6),
       (nextval('booking_info_sequence'), false, '2023-02-13 01:27:04.044067', '2024-03-14', '2024-03-28', true, 2, 3, 7),
       (nextval('booking_info_sequence'), false, '2023-06-01 01:27:04.044067', '2023-10-03', '2024-10-14', false, 1, null, 8),
       (nextval('booking_info_sequence'), false, '2023-03-15 01:27:04.044067', '2024-04-17', '2024-04-27', true, 1, 1, 9),
       (nextval('booking_info_sequence'), false, '2023-05-17 01:27:04.044067', '2023-07-18', '2023-07-29', true, 1, 2, 10),
       (nextval('booking_info_sequence'), false, '2023-04-22 01:27:04.044067', '2024-03-02', '2024-03-14', false, 2, null, 6);

insert into services (id, is_deleted, description, title, cost, category, duration)
values (nextval('services_sequence'), false, 'Ванны лекарственные', 'Бишофитовые ванны', 650, 'HYDROTHERAPY', 45),
       (nextval('services_sequence'), false, 'Ванны лекарственные', 'Ванны йодо-бромные «Ассоль»', 650, 'HYDROTHERAPY', 45),
       (nextval('services_sequence'), false, 'Ванны ароматические', 'Ванна «Клеопатра» (молоко+ваниль)', 650, 'HYDROTHERAPY', 45),
       (nextval('services_sequence'), false, 'Ванны воздушно-пузырьковые', 'Ванны пенно-лакричные (солодковые)', 900, 'HYDROTHERAPY', 45),
       (nextval('services_sequence'), false, 'Ванны лекарственные', 'Ванны с фитосолью «Эвкалипт»', 650, 'HYDROTHERAPY', 45),
       (nextval('services_sequence'), false, 'Толстокишечная эндоскопия', 'Колоноскопия', 3500, 'ENDOSCOPY', 60),
       (nextval('services_sequence'), false, 'Ректороманоскопия', 'Ректороманоскопия (РРС)', 2500, 'ENDOSCOPY', 60),
       (nextval('services_sequence'), false, 'Эзофагогастродуоденоскопия', 'Эзофагогастродуоденоскопия (ЭГДС)', 3200, 'ENDOSCOPY', 60),
       (nextval('services_sequence'), false, 'Массаж волосистой части головы', 'Массаж головы', 600, 'MASSAGE', 30),
       (nextval('services_sequence'), false, 'Массаж лица медицинский', 'Массаж лица', 600, 'MASSAGE', 30),
       (nextval('services_sequence'), false, 'Массаж рук', 'Массаж одной верхней конечности', 900, 'MASSAGE', 45),
       (nextval('services_sequence'), false, 'Массаж при заболеваниях позвоночника', 'Массаж грудного отдела', 900, 'MASSAGE', 30),
       (nextval('services_sequence'), false, 'Массаж ног', 'Массаж нижней конечности', 900, 'MASSAGE', 30),
       (nextval('services_sequence'), false, 'Ингаляторное введение препаратов', 'Ингаляции углекисло-минеральной водой', 350, 'INHALATIONS', 20),
       (nextval('services_sequence'), false, 'Ингаляторное введение препаратов', 'Ингаляции лекарственные', 350, 'INHALATIONS', 20),
       (nextval('services_sequence'), false, 'Респираторная терапия', 'Ингаляции кислородные', 350, 'INHALATIONS', 20);

insert into roles (id, description, title)
values (nextval('roles_sequence'), 'Роль сотрудника регистратуры', 'User');


