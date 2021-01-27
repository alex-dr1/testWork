insert into securities (id, emitent_title, name, reg_number, sec_id)
values (null,
'Публичное акционерное общество "Астраханская энергосбытовая компания"',
'"Астраханская ЭСК" ПАО', '1-01-55064-E', 'ASSB');

insert into securities (id, emitent_title, name, reg_number, sec_id)
values (null,
'Публичное  акционерное общество "Русская Аквакультура"',
'Русская Аквакультура ПАО ао', '1-01-04461-D', 'AQUA');

insert into securities (id, emitent_title, name, reg_number, sec_id)
values (null,
'Публичное акционерное общество "Мечел"',
'Мечел ПАО ап', '2-01-55005-E', 'MTLRP');

insert into history (id, close, num_trades, open, sec_id, trade_date)
values (null, 1.1, 81.0, 0.8, 'ASSB', to_date('2020-08-25', 'YYYY-MM-DD'));

insert into history (id, close, num_trades, open, sec_id, trade_date)
values (null, 2.1, 968.0, 221.5, 'AQUA', to_date('2020-08-25', 'YYYY-MM-DD'));

insert into history (id, close, num_trades, open, sec_id, trade_date)
values (null, 3.1, 2523.0, 73.65, 'MTLRP', to_date('2020-08-25', 'YYYY-MM-DD'));

insert into history (id, close, num_trades, open, sec_id, trade_date)
values (null, 4.1, 2523.0, 73.65, 'MTLRP', to_date('2021-01-18', 'YYYY-MM-DD'));