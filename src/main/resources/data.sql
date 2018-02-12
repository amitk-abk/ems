insert into attributetype (typeid, attributetype) values (1, 'select');
insert into attributetype (typeid, attributetype) values (2, 'integer');

insert into attribute (attributeid, description, displayname, name, enabled, sortable, visible, type_typeid) values (1, 'size of television set', 'TV Size', 'tvsize', 1, 1, 1, 1);
insert into attribute (attributeid, description, displayname, name, enabled, sortable, visible, type_typeid) values (2, 'Weight of television set in kg', 'TV Weight', 'tvweight', 1, 1, 1, 2);

insert into attributevalue (attributevalueid, value, attribute_attributeid) values (1, 38, 1);
insert into attributevalue (attributevalueid, value, attribute_attributeid) values (2, 62, 1);
insert into attributevalue (attributevalueid, value, attribute_attributeid) values (3, 54, 1);
insert into attributevalue (attributevalueid, value, attribute_attributeid) values (4, 15, 2);

insert into catalog (catalogid, catalogcode, catalogname, description, displayname) values (1, 'FLATTV1122', 'FLATTV', 'Flat television sets UHD', 'Flat TV');
insert into catalog (catalogid, catalogcode, catalogname, description, displayname) values (2, 'HMTHR1123', 'HOMETHEATRE', 'Surround sound 7.1 home theatre', '7.1 Home Theatre');

insert into product (productid, displayname, price, productcode, productdescription, productname, catalog_id) values (1, 'Flat 54 tv by Samsung', 51343.55, 'SAMSUNGFLAT54UHD', 'Flat 54 inch UHD TV by Samsung', 'Samsung Flat TV', 1);

insert into product_attribute (productid, attributevalueid) values (1, 1);
insert into product_attribute (productid, attributevalueid) values (1, 4);