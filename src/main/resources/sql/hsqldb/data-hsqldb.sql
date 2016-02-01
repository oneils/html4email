-- Digest Table
INSERT  INTO digest (digest_id, title) values (1, 'BackEnd Digest #1');
INSERT  INTO digest (digest_id, title) values (2, 'BackEnd Digest #2');
--------------------------------------------------------------

---- Topic Table
INSERT INTO topic (topic_id, topic_name, order_priority) VALUES (1, 'NEWS', 1);
INSERT INTO topic (topic_id, topic_name, order_priority) VALUES (2, 'Interesting', 2);
INSERT INTO topic (topic_id, topic_name, order_priority) VALUES (3, 'Videos', 3);
INSERT INTO topic (topic_id, topic_name, order_priority) VALUES (4, 'Audios', 4);

----------------------------------------------------------------

---- Article Table
INSERT  INTO article (article_id, title, url, topic_id) values (1, 'title1', 'http://localhost', 1);
INSERT  INTO article (article_id, title, url, topic_id) values (2, 'title2', 'http://localhost', 1);
INSERT  INTO article (article_id, title, url, topic_id) values (3, 'title3', 'http://localhost', 2);
----------------------------------------------------------------

-- Digest-Topic-Article table
--INSERT INTO topic_articles(topic_id, article_id) VALUES (1, 1);
--------------------------------------------------------------

-- Digest-Topic-Article table
INSERT INTO digest_topic_article (dta_id, digest_id, topic_id, article_id) VALUES (1, 1, 1, 1);
