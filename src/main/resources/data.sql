insert into region(id, name)
values (1, '{
  "uz": "Qoraqalpog`iston",
  "ru": "Қорақалпог`истон",
  "en": "Qoraqalpog`iston"
}');
insert into region(id, name)
values (2, '{
  "uz": "Andijon",
  "ru": "Андижан",
  "en": "Andijon"
}');
insert into region(id, name)
values (3, '{
  "uz": "Buxoro",
  "ru": "БУХОРО",
  "en": "Buxoro"
}');
insert into region(id, name)
values (4, '{
  "uz": "Jizzax",
  "ru": "ЖИЗЗАХ",
  "en": "Jizzax"
}');
insert into region(id, name)
values (5, '{
  "uz": "Qashqadaryo",
  "ru": "ҚАШҚАДАРЁ",
  "en": "Qashqadaryo"
}');
insert into region(id, name)
values (6, '{
  "uz": "Navoiy",
  "ru": "НАВОИЙ",
  "en": "Navoiy"
}');
insert into region(id, name)
values (7, '{
  "uz": "Namangan",
  "ru": "НАМАНГАН",
  "en": "Namangan"
}');
insert into region(id, name)
values (8, '{
  "uz": "Samarqand",
  "ru": "САМАРҚАНД",
  "en": "Samarqand"
}');
insert into region(id, name)
values (9, '{
  "uz": "Surxondaryo",
  "ru": "СУРХОНДАРЁ",
  "en": "Surxondaryo"
}');
insert into region(id, name)
values (10, '{
  "uz": "Sirdaryo",
  "ru": "СИРДАРЁ",
  "en": "Sirdaryo"
}');
insert into region(id, name)
values (11, '{
  "uz": "Toshkent",
  "ru": "Ташкент",
  "en": "Tashkent"
}');
insert into region(id, name)
values (12, '{
  "uz": "Farg`ona",
  "ru": "ФАРҒОНА",
  "en": "Farg`ona"
}');
insert into region(id, name)
values (13, '{
  "uz": "Xorazm",
  "ru": "Хорезм",
  "en": "Khorezm"
}');
insert into region(id, name)
values (14, '{
  "uz": "Toshkent shahar",
  "ru": "ГОРОД ТАШКЕНТ",
  "en": "Tashkent city"
}');

insert into district(name, region_id)
values ('{
  "uz": "Olmazor",
  "ru": "Olmazor",
  "en": "Olmazor"
}', 1);
insert into social_status(name)
VALUES ('{
  "uz": "Nogiron",
  "ru": "инвалид",
  "en": "invalid"
}');
insert into social_status(name)
VALUES ('{
  "uz": "Yolg`iz ona",
  "ru": "мать-одиночка",
  "en": "single mother"
}');
insert into social_status(name)
VALUES ('{
  "uz": "Boshqalar",
  "ru": "другие",
  "en": "others"
}');
insert into section(title)
VALUES ('{
  "uz": "Jinoiy",
  "ru": "Jinoiy",
  "en": "Jinoiy"
}');
insert into section(title)
VALUES ('{
  "uz": "Anti-terror",
  "ru": "Anti-terror",
  "en": "Anti-terror"
}');
insert into section(title)
VALUES ('{
  "uz": "Ichki ishlar",
  "ru": "Ichki ishlar",
  "en": "Ichki ishlar"
}');
insert into nation(name)
VALUES ('{
  "uz": "O`zbek",
  "ru": "Узбек",
  "en": "Uzbek"
}');
insert into nation(name)
VALUES ('{
  "uz": "Qozoq",
  "ru": "Казахский",
  "en": "Kazakh"
}');
insert into nation(name)
VALUES ('{
  "uz": "Rus",
  "ru": "Русь",
  "en": "Russian"
}');
insert into nation(name)
VALUES ('{
  "uz": "Tatar",
  "ru": "Tатарский",
  "en": "Tatar"
}');
insert into position(description, title)
values ('{
  "uz": "Serjant",
  "ru": "Serjant",
  "en": "Serjant"
}', '{
  "uz": "Serjant",
  "ru": "Serjant",
  "en": "Serjant"
}');
insert into position(description, title)
values ('{
  "uz": "Kapitan",
  "ru": "Kapitan",
  "en": "Kapitan"
}', '{
  "uz": "Kapitan",
  "ru": "Kapitan",
  "en": "Kapitan"
}');
insert into position(description, title)
values ('{
  "uz": "Mayor",
  "ru": "Mayor",
  "en": "Mayor"
}', '{
  "uz": "Mayor",
  "ru": "Mayor",
  "en": "Mayor"
}');
insert into position(description, title)
values ('{
  "uz": "Tinglovchi",
  "ru": "Tinglovchi",
  "en": "Tinglovchi"
}', '{
  "uz": "Tinglovchi",
  "ru": "Tinglovchi",
  "en": "Tinglovchi"
}');


insert into district(name, region_id)
values ('{
  "uz": "Olmazor",
  "ru": "Olmazor",
  "en": "Olmazor"
}', 1);

INSERT INTO district (region_id, name)
VALUES (1, '{
  "uz": "Amudaryo",
  "ru": "Амударья",
  "en": "Amudaryo"
}'),
       (1, '{
         "uz": "Beruniy tumani",
         "ru": "Беруний район",
         "en": "Beruniy district"
       }'),
       (1, '{
         "uz": "Kegayli tumani",
         "ru": "Кегайли район",
         "en": "Kegayli district"
       }'),
       (1, '{
         "uz": "Qonliko‘l tumani",
         "ru": "Қонликўл район",
         "en": "Qonliko‘l district"
       }'),
       (1, '{
         "uz": "Qorao‘zak tumani",
         "ru": "Қораўзак район",
         "en": "Qorao‘zak district"
       }'),
       (1, '{
         "uz": "Qo‘ng‘irot tumani",
         "ru": "Қўнғирот район",
         "en": "Qo‘ng‘irot district"
       }'),
       (1, '{
         "uz": "Nukus tumani",
         "ru": "Нукус район",
         "en": "Nukus district"
       }'),
       (1, '{
         "uz": "Nukus shahri",
         "ru": "Нукус город",
         "en": "Nukus city"
       }'),
       (1, '{
         "uz": "Taxtako‘pir shahri",
         "ru": "Нукус город",
         "en": "Nukus city"
       }');


insert into district(name, region_id)
values ('{
  "uz": "Taxtako‘pir shahri",
  "ru": "Нукус город",
  "en": "Nukus city"
}', 1),
       ('{
         "uz": " To‘rtko‘l shahri",
         "ru": " Торткол район",
         "en": " To‘rtko‘l district "
       }', 1),
       ('{
         "uz": " Xo‘jayli shahri",
         "ru": " Тортколрайон",
         "en": "Khojayli district "
       }', 1),
       ('{
         "uz": " CHimboy tumani ",
         "ru": " Чимбойский район ",
         "en": " Chimboy district "
       }', 1),
       ('{
         "uz": " SHumanay tumani ",
         "ru": " Шуманайский район ",
         "en": " SHumanay district "
       }', 1),
       ('{
         "uz": " Ellikqal‘a tumani ",
         "ru": " Элликкалинский район ",
         "en": " Ellikkala district "
       }', 1),
       ('{
         "uz": "Taxiatosh shahri",
         "ru": "Таxиатош город",
         "en": "Taxiatosh city"
       }', 1),
       ('{
         "uz": "Asaka shahri",
         "ru": "Асака город",
         "en": "Asaka city"
       }', 2),
       ('{
         "uz": " Andijon shahri ",
         "ru": " Андижан город ",
         "en": " Andijan city "
       }', 2)
        ,
       ('{
         "uz": " Baliqchi tumani ",
         "ru": " Баликчинский район ",
         "en": " Andijan district "
       }', 2)
        ,
       ('{
         "uz": " Asaka tumani ",
         "ru": " Асакинский район ",
         "en": " Asaka district "
       }', 2)
        ,
       ('{
         "uz": " Baliqchi tumani ",
         "ru": " Баликчинский район ",
         "en": " Balikchi district "
       }', 2)
        ,
       ('{
         "uz": " Buloqboshi tumani ",
         "ru": " Булокбошский район ",
         "en": " Buloqboshi district "
       }', 2)
        ,
       ('{
         "uz": " Bo‘z tumani ",
         "ru": " Бозский район ",
         "en": " Boz district "
       }', 2)
        ,
       ('{
         "uz": " Jalaquduq tumani",
         "ru": " Джалакудукский район ",
         "en": " Jalaquduq district "
       }', 2)
        ,
       ('{
         "uz": " Izbosgan tumani ",
         "ru": " Избосганский район ",
         "en": " Izbosgan district "
       }', 2)
        ,
       ('{
         "uz": " Qorasuv shahri ",
         "ru": " Карасув Город ",
         "en": " Karasuv City "
       }', 2)
        ,
       ('{
         "uz": " Qo‘rg‘ontepa tumani ",
         "ru": " Кургантепа район",
         "en": "Qurghonteppa district "
       }', 2)
        ,
       ('{
         "uz": " Marhamat tumani ",
         "ru": " Мархаматский район ",
         "en": " Marhamat district"
       }', 2)
        ,
       ('{
         "uz": " Oltinko‘l tumani ",
         "ru": " Алтынкольский район ",
         "en": "Altynkol district"
       }', 2)
        ,
       ('{
         "uz": " Paxtaobod tumani ",
         "ru": " Пахтаабадский район ",
         "en": " Pakhtaobod district "
       }', 2)
        ,
       ('{
         "uz": " Ulug‘nor tumani ",
         "ru": " Улугнорский район ",
         "en": " Ulugnor district "
       }', 2)
        ,
       ('{
         "uz": " Xonabod tumani ",
         "ru": " Ханабадский район ",
         "en": " Khanabad district "
       }', 2)
        ,
       ('{
         "uz": " Xo‘jaobod shahri ",
         "ru": " Ходжаабад город ",
         "en": " Khojaabad city "
       }', 2)
        ,
       ('{
         "uz": " Shaxrixon tumani ",
         "ru": " Шахриханский район ",
         "en": " Shahrihan district "
       }', 2)
        ,
       ('{
         "uz": " Buxoro shahri ",
         "ru": " Бухара город ",
         "en": " Bukhara city "
       }', 3)
        ,
       ('{
         "uz": " Vobkent tumani ",
         "ru": " Вобкентский район ",
         "en": " Vobkent district "
       }', 3)
        ,
       ('{
         "uz": " G‘ijduvon tumani ",
         "ru": " Гиждуванский район ",
         "en": " Gijduvan district "
       }', 3)
        ,
       ('{
         "uz": " Jondor tumani ",
         "ru": " Жондорский район ",
         "en": " Jondor district "
       }', 3)
        ,
       ('{
         "uz": " Kogon tumani ",
         "ru": " Когонский район ",
         "en": " Kogon district "
       }', 3)
        ,
       ('{
         "uz": " Kogon shahri ",
         "ru": " Город Каган ",
         "en": " Kagan city "
       }', 3)
        ,
       ('{
         "uz": " Qorako‘l tumani ",
         "ru": " Каракольский район ",
         "en": " Karakol district "
       }', 3)
        ,
       ('{
         "uz": " Qorovulbozor tumani ",
         "ru": " Каравулбозорский район ",
         "en": " Karavulbozor district "
       }', 3)
        ,
       ('{
         "uz": " Olot tumani ",
         "ru": " Олотский район ",
         "en": " Olot district "
       }', 3)
        ,
       ('{
         "uz": " Peshku tumani ",
         "ru": " Пешкунский район ",
         "en": " Peshku district "
       }', 3)
        ,
       ('{
         "uz": " Romitan tumani ",
         "ru": " Ромитан район ",
         "en": " Romitan district "
       }', 3)
        ,
       ('{
         "uz": " Shofirkon tumani ",
         "ru": " Шофиркон район ",
         "en": " Shofirkon district "
       }', 3)
        ,
       ('{
         "uz": " Arnasoy tumani ",
         "ru": " Арнасой район ",
         "en": " Arnasoy district "
       }', 4)
        ,
       ('{
         "uz": " Baxmal tumani ",
         "ru": " Бахмал район ",
         "en": " Bakhmal district "
       }', 4)
        ,
       ('{
         "uz": " G‘allaorol tumani ",
         "ru": " Ғаллаорол район ",
         "en": " Gallaorol district "
       }', 4)
        ,
       ('{
         "uz": "Do‘stlik tumani ",
         "ru": "Дўстлик район ",
         "en": "Do‘stlik district "
       }', 4)
        ,
       ('{
         "uz": "Sh.Rashidov tumani ",
         "ru": "Ш.Рашидов район ",
         "en": "Sh.Rashidov district "
       }', 4)
        ,
       ('{
         "uz": "Jizzax shahri ",
         "ru": "Жиззах район ",
         "en": "Jizzakh city "
       }', 4)
        ,
       ('{
         "uz": "Zarbdor tumani ",
         "ru": "Зарбдор район ",
         "en": "Zarbdor district "
       }', 4)
        ,
       ('{
         "uz": "Zafarobod tumani ",
         "ru": "Зафаробод район ",
         "en": "Zafarobod district "
       }', 4)
        ,
       ('{
         "uz": "Zomin tumani ",
         "ru": "Зомин район ",
         "en": "Zomin district "
       }', 4)
        ,
       ('{
         "uz": "Mirzacho‘l tumani ",
         "ru": "Мирзачўл район ",
         "en": "Mirzacho‘l district "
       }', 4)
        ,
       ('{
         "uz": "Paxtakor tumani ",
         "ru": "Пахтакор район ",
         "en": "Paxtakor district "
       }', 4)
        ,
       ('{
         "uz": "Forish tumani ",
         "ru": "Фориш район ",
         "en": "Forish district "
       }', 4)
        ,
       ('{
         "uz": "Yangiobod tumani ",
         "ru": "Янгиобод район ",
         "en": "Yangiobod district "
       }', 4)
        ,
       ('{
         "uz": "G‘uzor tumani ",
         "ru": "Ғузор район ",
         "en": "Guzar district "
       }', 5)
        ,
       ('{
         "uz": "Dehqonobod tumani ",
         "ru": "Деҳқонобод район ",
         "en": "Dehqonobod district "
       }', 5)
        ,
       ('{
         "uz": "Dehqonobod tumani ",
         "ru": "Деҳқонобод район ",
         "en": "Dehqonobod district "
       }', 5)
        ,
       ('{
         "uz": "Qamashi tumani ",
         "ru": "Қамаши район ",
         "en": "Qamashi district "
       }', 5)
        ,
       ('{
         "uz": "Qarshi tumani ",
         "ru": "Қарши район ",
         "en": "Qarshi district "
       }', 5)
        ,
       ('{
         "uz": "Qarshi tumani ",
         "ru": "Қарши район",
         "en": "Qarshi district "
       }', 5)
        ,
       ('{
         "uz": "Kasbi tumani ",
         "ru": "Касби район",
         "en": "Kasbi district "
       }', 5)
        ,
       ('{
         "uz": "Kitob tumani ",
         "ru": "Китоб район",
         "en": "Kitob district "
       }', 5)
        ,
       ('{
         "uz": "Koson tumani ",
         "ru": "Косон район",
         "en": "Koson district "
       }', 5)
        ,
       ('{
         "uz": "Mirishkor tumani ",
         "ru": "Миришкор район",
         "en": "Mirishkor district "
       }', 5)
        ,
       ('{
         "uz": "Muborak tumani ",
         "ru": "Муборак район",
         "en": "Muborak district "
       }', 5)
        ,
       ('{
         "uz": "Nishon tumani ",
         "ru": "Нишон район",
         "en": "Nishon district "
       }', 5)
        ,
       ('{
         "uz": "Chiroqchi tumani ",
         "ru": "Нишон район",
         "en": "Chiroqchi district "
       }', 5)
        ,
       ('{
         "uz": "Shahrisabz tumani ",
         "ru": "Шаҳрисабз район",
         "en": "Shahrisabz district "
       }', 5)
        ,
       ('{
         "uz": "Yakkabog tumani ",
         "ru": "Яккабог район",
         "en": "Yakkabog district "
       }', 5)
        ,
       ('{
         "uz": "Zarafshon tumani ",
         "ru": "Зарафшон район",
         "en": "Zarafshon district "
       }', 6)
        ,
       ('{
         "uz": "Karmana tumani ",
         "ru": "Кармана район",
         "en": "Karmana district "
       }', 6)
        ,
       ('{
         "uz": "Qiziltepa tumani ",
         "ru": "Қизилтепа район",
         "en": "Qiziltepa district "
       }', 6)
        ,
       ('{
         "uz": "Konimex tumani ",
         "ru": "Конимех район",
         "en": "Konimex district "
       }', 6)
        ,
       ('{
         "uz": "Navbahor tumani ",
         "ru": "Навбаҳор район",
         "en": "Navbahor district "
       }', 6)
        ,
       ('{
         "uz": "Navoiy tumani ",
         "ru": "Навоий район",
         "en": "Navoiy district "
       }', 6)
        ,
       ('{
         "uz": "Nurota tumani ",
         "ru": "Нурота район",
         "en": "Nurota district "
       }', 6)
        ,
       ('{
         "uz": "Tomdi tumani ",
         "ru": "Томди район",
         "en": "Tomdi district "
       }', 6)
        ,
       ('{
         "uz": "Uchquduq tumani ",
         "ru": "Учқудуқ район",
         "en": "Uchquduq district "
       }', 6)
        ,
       ('{
         "uz": "Xatirchi tumani ",
         "ru": "Хатирчи район",
         "en": "Xatirchi district "
       }', 6)
        ,
       ('{
         "uz": "Kosonsoy tumani ",
         "ru": "Косонсой район",
         "en": "Kosonsoy district "
       }', 7)
        ,
       ('{
         "uz": "Mingbuloq tumani ",
         "ru": "Мингбулок район",
         "en": "Mingbuloq district "
       }', 7)
        ,
       ('{
         "uz": "Namangan tumani ",
         "ru": "Наманган район",
         "en": "Namangan district "
       }', 7)
        ,
       ('{
         "uz": "Namangan shahri",
         "ru": "Наманган город",
         "en": "Namangan city"
       }', 7)
        ,
       ('{
         "uz": "Norin tumani ",
         "ru": "Норин район",
         "en": "Norin district "
       }', 7)
        ,
       ('{
         "uz": "Pop tumani ",
         "ru": "Поп район",
         "en": "Pop district "
       }', 7)
        ,
       ('{
         "uz": "To‘raqo‘rg‘on tumani ",
         "ru": "Торакоргон район",
         "en": "Torakorgon district "
       }', 7)
        ,
       ('{
         "uz": "Uychi tumani ",
         "ru": "Уйчи район",
         "en": "Uychi district "
       }', 7)
        ,
       ('{
         "uz": "Uychi tumani ",
         "ru": "Уйчи район",
         "en": "Uychi district "
       }', 7)
        ,
       ('{
         "uz": "Uchqo‘rg‘on tumani ",
         "ru": "Учкурган район",
         "en": "Uchkurgan district "
       }', 7)
        ,
       ('{
         "uz": "Chortoq tumani ",
         "ru": "Чартак район",
         "en": "Chartak district "
       }', 7)
        ,
       ('{
         "uz": "Chust tumani ",
         "ru": "Чуст район",
         "en": "Chust district "
       }', 7)
        ,
       ('{
         "uz": "Yangiqo‘rg‘on tumani ",
         "ru": "Янгикурган  район",
         "en": "Yangikurgan district "
       }', 7)
        ,
       ('{
         "uz": "Bulung‘ur tumani ",
         "ru": "Булунгур район",
         "en": "Bulungur district "
       }', 8)
        ,
       ('{
         "uz": "Jomboy tumani ",
         "ru": "Джомбой район",
         "en": "Jomboy district "
       }', 8)
        ,
       ('{
         "uz": "Ishtixon tumani",
         "ru": "Иштихон район",
         "en": "Ishtixon district "
       }', 8)
        ,
       ('{
         "uz": "Kattaqo‘rg‘on tumani",
         "ru": "Каттакурган район",
         "en": "Kattakurgan district "
       }', 8)
        ,
       ('{
         "uz": "Kattaqo‘rg‘on shahri",
         "ru": "Каттакурган город",
         "en": "Kattakurgan city "
       }', 8)
        ,
       ('{
         "uz": "Qo‘shrabot tumani",
         "ru": "Қўшработ район",
         "en": "Qoshrabot district "
       }', 8)
        ,
       ('{
         "uz": "Narpay tumani",
         "ru": "Нарпай район",
         "en": "Narpay district "
       }', 8)
        ,
       ('{
         "uz": "Nurabod tumani",
         "ru": "Нурабад район",
         "en": "Nurabod district "
       }', 8)
        ,
       ('{
         "uz": "Oqdaryo tumani",
         "ru": "Aқдарё район",
         "en": "Oqdaryo district "
       }', 8)
        ,
       ('{
         "uz": "Payariq tumani",
         "ru": "Паяриқ район",
         "en": "Payariq district "
       }', 8)
        ,
       ('{
         "uz": "Pastarg‘om tumani",
         "ru": "Пастаргом район",
         "en": "Pastarg‘om district "
       }', 8)
        ,
       ('{
         "uz": "Paxtachi tumani",
         "ru": "Пахтачи район",
         "en": "Pakhtachi district "
       }', 8)
        ,
       ('{
         "uz": "Samarqand tumani",
         "ru": "Самарқанд район",
         "en": "Samarqand district "
       }', 8)
        ,
       ('{
         "uz": "Samarqand shahri",
         "ru": "Самарқанд город",
         "en": "Samarqand city"
       }', 8)
        ,
       ('{
         "uz": "Toyloq tumani",
         "ru": "Тойлоқ район",
         "en": "Toyloq district "
       }', 8)
        ,
       ('{
         "uz": "Urgut tumani",
         "ru": "Ургут район",
         "en": "Urgut district "
       }', 8)
        ,
       ('{
         "uz": "Angor tumani",
         "ru": "Ангор район",
         "en": "Angor district "
       }', 9)
        ,
       ('{
         "uz": "Boysun tumani",
         "ru": "Байсун район",
         "en": "Boysun district "
       }', 9)
        ,
       ('{
         "uz": "Denov tumani",
         "ru": "Денов район",
         "en": "Denov district "
       }', 9)
        ,
       ('{
         "uz": "Jarqo‘rg‘on tumani",
         "ru": "Джаркоргон район",
         "en": "Jarqorgon district"
       }', 9)
        ,
       ('{
         "uz": "Qiziriq tumani",
         "ru": "Қизириқ район",
         "en": "Qiziriq district"
       }', 9)
        ,
       ('{
         "uz": "Qo‘mqo‘rg‘on tumani",
         "ru": "Қўмқўрғон район",
         "en": "Qumqorgon district"
       }', 9)
        ,
       ('{
         "uz": "Muzrabot tumani",
         "ru": "Музработ район",
         "en": "Muzrabot district"
       }', 9)
        ,
       ('{
         "uz": "Oltinsoy tumani",
         "ru": "Олтинсой район",
         "en": "Oltinsoy district"
       }', 9)
        ,
       ('{
         "uz": "Sariosiyo tumani",
         "ru": "Сариосиё район",
         "en": "Sariosiyo district"
       }', 9)
        ,
       ('{
         "uz": "Termiz tumani",
         "ru": "Термез район",
         "en": "Termiz district"
       }', 9)
        ,
       ('{
         "uz": "Termiz shahri",
         "ru": "Термез город",
         "en": "Termiz city"
       }', 9)
        ,
       ('{
         "uz": "Uzun tumani",
         "ru": "Узун район",
         "en": "Uzun district"
       }', 9)
        ,
       ('{
         "uz": "Sherobod tumani",
         "ru": "Шеробод район",
         "en": "Sherobod district"
       }', 9)
        ,
       ('{
         "uz": "Sho‘rchi tumani",
         "ru": "Шорчи район",
         "en": "Shorchi district"
       }', 9)
        ,
       ('{
         "uz": "Bandixon tumani",
         "ru": "Бандихон район",
         "en": "Bandixon district"
       }', 9)
        ,
       ('{
         "uz": "Boyovut tumani",
         "ru": "Бойовут район",
         "en": "Boyovut district"
       }', 10)
        ,
       ('{
         "uz": "Guliston tumani",
         "ru": "Гулистон район",
         "en": "Guliston district"
       }', 10)
        ,
       ('{
         "uz": "Guliston shahri",
         "ru": "Гулистон город",
         "en": "Guliston city"
       }', 10)
        ,
       ('{
         "uz": "Mirzaobod tumani",
         "ru": "Мирзаабад район",
         "en": "Mirzaobod district"
       }', 10)
        ,
       ('{
         "uz": "Oqoltin tumani",
         "ru": "Оқолтин район",
         "en": "Oqoltin district"
       }', 10)
        ,
       ('{
         "uz": "Sayxunobod tumani",
         "ru": "Сайксунобод район",
         "en": "Sayxunobod district"
       }', 10)
        ,
       ('{
         "uz": "Sardoba tumani",
         "ru": "Сардоба район",
         "en": "Sardoba district"
       }', 10)
        ,
       ('{
         "uz": "Sirdaryo tumani",
         "ru": "Сырдарья район",
         "en": "Sirdaryo district"
       }', 10)
        ,
       ('{
         "uz": "Xavos tumani",
         "ru": "Хавос район",
         "en": "Xavos district"
       }', 10)
        ,
       ('{
         "uz": "Shirin tumani",
         "ru": "Ширин район",
         "en": "Shirin district"
       }', 10)
        ,
       ('{
         "uz": "Yangier tumani",
         "ru": "Янгиер район",
         "en": "Yangier district"
       }', 10)
        ,
       ('{
         "uz": "Angiren shahri",
         "ru": "Ангирен город",
         "en": "Angiren city"
       }', 11)
        ,
       ('{
         "uz": "Bekabod tumani",
         "ru": "Бекабод район",
         "en": "Bekabod district"
       }', 11)
        ,
       ('{
         "uz": "Bekabod shahri",
         "ru": "Бекабод город",
         "en": "Bekabod city"
       }', 11)
        ,
       ('{
         "uz": "Bo‘ka tumani",
         "ru": "Бока район",
         "en": "Boka district"
       }', 11)
        ,
       ('{
         "uz": "Bo‘stonliq tumani",
         "ru": "Бостонлик район",
         "en": "Bostonliq district"
       }', 11)
        ,
       ('{
         "uz": "Zangiota tumani",
         "ru": "Зангиота район",
         "en": "Zangiota district"
       }', 11)
        ,
       ('{
         "uz": "Qibray tumani",
         "ru": "Қибрай район",
         "en": "Qibray district"
       }', 11)
        ,
       ('{
         "uz": "Quyichirchiq tumani",
         "ru": "Қуйичирчиқ район",
         "en": "Quyichirchiq district"
       }', 11)
        ,
       ('{
         "uz": "Oqqo‘rg‘on tumani",
         "ru": "Оққўрғон район",
         "en": "Oqqorgon district"
       }', 11)
        ,
       ('{
         "uz": "Olmaliq shahri",
         "ru": "Олмалиқ город",
         "en": "Olmaliq city"
       }', 11)
        ,
       ('{
         "uz": "Ohangaron tumani",
         "ru": "Охангарон район",
         "en": "Ohangaron district"
       }', 11)
        ,
       ('{
         "uz": "Parkent tumani",
         "ru": "Паркент район",
         "en": "Parkent district"
       }', 11)
        ,
       ('{
         "uz": "Piskent tumani",
         "ru": "Пискент район",
         "en": "Piskent district"
       }', 11)
        ,
       ('{
         "uz": "O‘rtachirchiq tumani",
         "ru": "Ортачирчик район",
         "en": "Ortachirchiq district"
       }', 11)
        ,
       ('{
         "uz": "Chinoz tumani",
         "ru": "Чиноз район",
         "en": "Chinoz district"
       }', 11)
        ,
       ('{
         "uz": "Chirchiq tumani",
         "ru": "Чирчик район",
         "en": "Chirchiq district"
       }', 11)
        ,
       ('{
         "uz": "Yuqorichirchiq tumani",
         "ru": "Юкоричирчик район",
         "en": "Yuqorichirchiq district"
       }', 11)
        ,
       ('{
         "uz": "Yangiyo‘l tumani",
         "ru": "Янгиёл район",
         "en": "Yangiyo‘l district"
       }', 11)
        ,
       ('{
         "uz": "Ohangaron shahri",
         "ru": "Оҳангарон город",
         "en": "Ohangaron city"
       }', 11)
        ,
       ('{
         "uz": "Yangiyo‘l shahri",
         "ru": "Янгийўл город",
         "en": "Yangiyol city"
       }', 11)
        ,
       ('{
         "uz": "Toshkent shahri",
         "ru": "Тошкент город",
         "en": "Toshkent city"
       }', 11)
        ,
       ('{
         "uz": "Beshariq tumani",
         "ru": "Бешарик район",
         "en": "Beshariq district"
       }', 12)
        ,
       ('{
         "uz": "Bog‘dod tumani",
         "ru": "Богдод район",
         "en": "Bogdod district"
       }', 12)
        ,
       ('{
         "uz": "Buvayda tumani",
         "ru": "Бувайда район",
         "en": "Buvayda district"
       }', 12)
        ,
       ('{
         "uz": "Dang‘ara tumani",
         "ru": "Дангара район",
         "en": "Dangara district"
       }', 12)
        ,
       ('{
         "uz": "Yozyovon tumani",
         "ru": "Ёзёвон район",
         "en": "Yozyovon district"
       }', 12)
        ,
       ('{
         "uz": "Quva tumani",
         "ru": "Қува район",
         "en": "Quva district"
       }', 12)
        ,
       ('{
         "uz": "Quvasoy tumani",
         "ru": "Кувасой район",
         "en": "Quvasoy district"
       }', 12)
        ,
       ('{
         "uz": "Qo‘qon shahri",
         "ru": "Қоқон город",
         "en": "Qoqon city"
       }', 12)
        ,
       ('{
         "uz": "Qo‘shtepa tumani",
         "ru": "Қўштепа район",
         "en": "Qoshtepa district"
       }', 12)
        ,
       ('{
         "uz": "Marg‘ilon tumani",
         "ru": "Маргилон район",
         "en": "Margilon district"
       }', 12)
        ,
       ('{
         "uz": "Oltiariq tumani",
         "ru": "Олтиариқ район",
         "en": "Oltiariq district"
       }', 12)
        ,
       ('{
         "uz": "Rishton tumani",
         "ru": "Риштон район",
         "en": "Rishton district"
       }', 12)
        ,
       ('{
         "uz": "So‘x tumani",
         "ru": "Сўх район",
         "en": "Sox district"
       }', 12)
        ,
       ('{
         "uz": "Toshloq tumani",
         "ru": "Тошлок район",
         "en": "Toshloq district"
       }', 12)
        ,
       ('{
         "uz": "Uchko‘prik tumani",
         "ru": "Учкоприк район",
         "en": "Uchkoprik district"
       }', 12)
        ,
       ('{
         "uz": "O‘zbekiston tumani",
         "ru": "Узбекистан район",
         "en": "Uzbekiston district"
       }', 12)
        ,
       ('{
         "uz": "Farg‘ona tumani",
         "ru": "Фаргона район",
         "en": "Fargona district"
       }', 12)
        ,
       ('{
         "uz": "Farg‘ona shahri",
         "ru": "Фаргона город",
         "en": "Fargona city"
       }', 12)
        ,
       ('{
         "uz": "Furqat tumani",
         "ru": "Фуркат район",
         "en": "Furqat district"
       }', 12)
        ,
       ('{
         "uz": "Bog‘ot tumani",
         "ru": "Богот район",
         "en": "Bogot district"
       }', 13)
        ,
       ('{
         "uz": "Gurlan tumani",
         "ru": "Богот район",
         "en": "Bogot district"
       }', 13)
        ,
       ('{
         "uz": "Qo‘shko‘pir tumani",
         "ru": "Кошкопир район",
         "en": "Qoshkopir district"
       }', 13)
        ,
       ('{
         "uz": "Urganch tumani",
         "ru": "Ургенч район",
         "en": "Urganch district"
       }', 13)
        ,
       ('{
         "uz": "Urganch shahri",
         "ru": "Ургенч город",
         "en": "Urganch city"
       }', 13)
        ,
       ('{
         "uz": "Xiva tumani",
         "ru": "Xивa район",
         "en": "Khiva district"
       }', 13)
        ,
       ('{
         "uz": "Xazarasp tumani",
         "ru": "Хазарасп район",
         "en": "Khazarasp district"
       }', 13)
        ,
       ('{
         "uz": "Xonqa tumani",
         "ru": "Хонқа район",
         "en": "Xonqa district"
       }', 13)
        ,
       ('{
         "uz": "Shavot tumani",
         "ru": "Шавот район",
         "en": "Shavot district"
       }', 13)
        ,
       ('{
         "uz": "Yangiariq tumani",
         "ru": "Янгиариқ район",
         "en": "Yangiariq district"
       }', 13)
        ,
       ('{
         "uz": "Yangibozor tumani",
         "ru": "Янгибозор район",
         "en": "Yangibozor district"
       }', 13)
        ,
       ('{
         "uz": "Bektimer tumani",
         "ru": "Бектимер район",
         "en": "Bektimer district"
       }', 14)
        ,
       ('{
         "uz": "M.Ulug‘bek tumani",
         "ru": "М.Улуғбек район",
         "en": "M.Ulug‘bek district"
       }', 14)
        ,
       ('{
         "uz": "Mirobod tumani",
         "ru": "Миробод район",
         "en": "Mirobod district"
       }', 14)
        ,
       ('{
         "uz": "Olmazor tumani",
         "ru": "Олмазор район",
         "en": "Olmazor district"
       }', 14)
        ,
       ('{
         "uz": "Sergeli tumani",
         "ru": "Сергели район",
         "en": "Sergeli district"
       }', 14)
        ,
       ('{
         "uz": "Uchtepa tumani",
         "ru": "Учтепа район",
         "en": "Uchtepa district"
       }', 14)
        ,
       ('{
         "uz": "Yashnobod tumani",
         "ru": "Яшнобод район",
         "en": "Yashnobod district"
       }', 14)
        ,
       ('{
         "uz": "Chilonzor tumani",
         "ru": "Чилонзор район",
         "en": "Chilonzor district"
       }', 14)
        ,
       ('{
         "uz": "Shayxontohur tumani",
         "ru": "Шайxонтохур район",
         "en": "Shayxontohur district"
       }', 14)
        ,
       ('{
         "uz": "Yunusobod tumani",
         "ru": "Юнусабад район",
         "en": "Yunusobod district"
       }', 14)
        ,
       ('{
         "uz": "Yakkasaroy tumani",
         "ru": "Яккасарой район",
         "en": "Yakkasaroy district"
       }', 14);




