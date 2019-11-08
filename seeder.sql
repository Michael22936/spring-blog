USE spring_blog_db;

INSERT INTO dogs(age, name, reside_state) VALUE 
    (2, 'Chuck', 'TX'),
    (5, 'Fred', 'OH'),
    (3, 'Bud', 'TN'),
    (10, 'Bailey', 'AL'),
    (11, 'Lexie', 'TX'),
    (1, 'Snickers', 'TX'),
    (6, 'Red', 'FL'),
    (8, 'Barney', 'CA'),
    (12, 'Bowser', 'TX');

insert into posts (title, body, post_details_id) values ('Computer Systems Analyst II', 'Srostki', 11);
insert into posts (title, body, post_details_id) values ('Quality Engineer', 'Bugembe', 12);
insert into posts (title, body, post_details_id) values ('VP Product Management', 'Sabangan', 3);
insert into posts (title, body, post_details_id) values ('Associate Professor', 'San Cosme y Damián',4);
insert into posts (title, body, post_details_id) values ('Web Designer III', 'Wukari', 5);
insert into posts (title, body, post_details_id) values ('Cost Accountant', 'Changning', 6);
insert into posts (title, body, post_details_id) values ('Actuary', 'Palestina de los Altos', 7);
insert into posts (title, body, post_details_id) values ('Paralegal', 'Tākestān', 8);
insert into posts (title, body, post_details_id) values ('Account Coordinator', 'A dos Negros', 9);
insert into posts (title, body, post_details_id) values ('Programmer III', 'Selouane', 10);
insert into posts(title, body, post_details_id) VALUES ('cats', 'i love cats', 1);
insert into posts(title, body, post_details_id)values ('more cats', 'i really like cats', 2);

insert into post_image(image_title, url, post_id) values ('cats', 'https://placekitten.com/200/300', 1);
insert into post_image(image_title, url, post_id) values ('cats', 'https://placekitten.com/200/300', 1);
insert into post_image(image_title, url, post_id) values ('cats', 'https://placekitten.com/200/300', 1);
insert into post_image(image_title, url, post_id) values ('cats', 'https://placekitten.com/200/300', 2);
insert into post_image(image_title, url, post_id) values ('cats', 'https://placekitten.com/200/300', 2);
insert into post_image(image_title, url, post_id) values ('cats', 'https://placekitten.com/200/300', 2);


insert into post_details (history_of_post, is_awesome, topic_description) VALUE ('this post is old',1,'this post was made old');
insert into post_details (history_of_post, is_awesome, topic_description) VALUE ('this post is old',0,'this post was made old');
insert into post_details (history_of_post, is_awesome, topic_description) VALUE ('this post is old',1,'this post was made old');
insert into post_details (history_of_post, is_awesome, topic_description) VALUE ('this post is old',1,'this post was made old');
insert into post_details (history_of_post, is_awesome, topic_description) VALUE ('this post is old',0,'this post was made old');
insert into post_details (history_of_post, is_awesome, topic_description) VALUE ('this post is old',0,'this post was made old');
insert into post_details (history_of_post, is_awesome, topic_description) VALUE ('this post is old',1,'this post was made old');
insert into post_details (history_of_post, is_awesome, topic_description) VALUE ('this post is old',1,'this post was made old');
insert into post_details (history_of_post, is_awesome, topic_description) VALUE ('this post is old',1,'this post was made old');
insert into post_details (history_of_post, is_awesome, topic_description) VALUE ('this post is old',1,'this post was made old');
insert into post_details (history_of_post, is_awesome, topic_description) VALUE ('this post is old',1,'this post was made old');
insert into post_details (history_of_post, is_awesome, topic_description) VALUE ('this post is old',1,'this post was made old');

insert into tag(name) values ('Silly');
insert into tag(name) values ('Funny');
insert into tag(name) values ('Humor');

insert into post_tag(tag_id, post_id) VALUES
(1,1),
(2,1),
(3,2),
(2,2)