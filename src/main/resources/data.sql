INSERT INTO currency (id, code, code_description, country_name, rate) VALUES
('1', 'KES', 'Kenyan Shillings', 'Kenya', 6.10),
('2', 'MWK', 'Malawian Kwacha', 'Malawi', 42.50);

INSERT INTO ussd_menu (menu_number, menu_description) VALUES
('1', 'Welcome to Mama Money! Where would you like to send money to? \n1) Kenya\n2) Malawi'),
('2', 'How much money (in Rands) would you like to send to %s?'),
('3', 'Your person you are sending to will receive: %s %s\n1) Ok'),
('4', 'Thank you for using Mana Money');