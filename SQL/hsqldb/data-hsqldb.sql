-- Digest Table
INSERT  INTO digest (title) values ('BackEnd Digest #1');
INSERT  INTO digest (title) values ('BackEnd Digest #2');
--------------------------------------------------------------

-- Topic Table
INSERT INTO topic (topic_name, order_priority) VALUES ("NEWS", 1);
INSERT INTO topic (topic_name, order_priority) VALUES ("Interesting", 2);
INSERT INTO topic (topic_name, order_priority) VALUES ("Videos", 3);
INSERT INTO topic (topic_name, order_priority) VALUES ("Audios", 4);
--------------------------------------------------------------


-- Article Table
insert  INTO article (title, url, topic_id) values ('title1', 'http://localhost', 1);
insert  INTO article (title, url, topic_id) values ('title2', 'http://localhost', 1);
insert  INTO article (title, url, topic_id) values ('title3', 'http://localhost', 2);
--------------------------------------------------------------

-- Digest-Topic-Article table
--INSERT INTO topic_articles(topic_id, article_id) VALUES (1, 7);
--------------------------------------------------------------

-- Digest-Topic-Article table
