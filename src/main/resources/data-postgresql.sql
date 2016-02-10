-- Digest Table
INSERT  INTO digest (title) values ('BackEnd Digest #1');
INSERT  INTO digest (title) values ('BackEnd Digest #2');
--------------------------------------------------------------

---- Topic Table
INSERT INTO topic (topic_name, order_priority) VALUES ('NEWS', 1);
INSERT INTO topic (topic_name, order_priority) VALUES ('Interesting', 2);
INSERT INTO topic (topic_name, order_priority) VALUES ('Videos', 3);
INSERT INTO topic (topic_name, order_priority) VALUES ('Audios', 4);

----------------------------------------------------------------

---- Article Table
INSERT  INTO article (title, url, topic_id) values ('title1', 'http://localhost', 1);
INSERT  INTO article (title, url, topic_id) values ('title2', 'http://localhost', 1);
INSERT  INTO article (title, url, topic_id) values ('title3', 'http://localhost', 2);
INSERT  INTO article (title, url, topic_id) values ('title4', 'http://localhost', 3);
----------------------------------------------------------------

-- Digest-Topic-Article table
INSERT INTO topic_articles(topic_id, article_id) VALUES (1, 1);
INSERT INTO topic_articles(topic_id, article_id) VALUES (2, 2);
--------------------------------------------------------------

-- Digest-Topic-Article table
INSERT INTO digest_topic_article (dta_id, digest_id, topic_id, article_id) VALUES (1, 1, 1, 1);