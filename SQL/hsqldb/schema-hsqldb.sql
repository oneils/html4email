-- Digest Table
DROP TABLE IF EXISTS digest;
CREATE TABLE digest (
  digest_id serial PRIMARY KEY,
  title varchar(50) NOT NULL,
  published_date DATE,
  created_date DATE default current_date
);
CREATE INDEX "title" ON "public"."digest" USING btree( "title" Asc NULLS Last );
--------------------------------------------------------------

-- Topic Table
DROP TABLE IF EXISTS topic;
CREATE TABLE topic (
  topic_id serial PRIMARY KEY,
  topic_name varchar(50) NOT NULL,
  order_priority Integer NOT NULL
);
CREATE INDEX "topic_name" ON "public"."topic" USING btree( "topic_name" Asc NULLS Last );
--------------------------------------------------------------


-- Article Table
DROP TABLE IF EXISTS article;
CREATE TABLE article (
    article_id serial PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    url VARCHAR NOT NULL,
    description TEXT,
    created_date DATE default current_date,
    updated_date DATE,
    topic_id INT NOT NULL,
    FOREIGN KEY (topic_id) REFERENCES topic (topic_id)
);
CREATE INDEX "title" ON "public"."article" USING btree( "title" Asc NULLS Last );
CREATE INDEX "description" ON "public"."article" USING btree( "description" Asc NULLS Last );
--------------------------------------------------------------

-- Digest-Topic-Article table
DROP TABLE IF EXISTS topic_articles;
CREATE TABLE "public"."topic_articles" (
  "topic_id" serial NOT NULL,
  "article_id" Integer NOT NULL,
  PRIMARY KEY ( "topic_id", "article_id" ) );

DROP INDEX IF EXISTS "index_topic_article_id";
CREATE INDEX "index_topic_article_id" ON "public"."topic_articles" USING btree( "topic_id", "article_id" );
--------------------------------------------------------------

-- Digest-Topic-Article table
DROP TABLE IF EXISTS digest_topic_article;
CREATE TABLE "public"."digest_topic_article" (
  "dta_id" serial NOT NULL,
  "digest_id" Integer NOT NULL,
  "topic_id" Integer NOT NULL,
  "article_id" Integer NOT NULL,
  PRIMARY KEY ( "dta_id" ) );

DROP INDEX IF EXISTS "index_article_id";
CREATE INDEX "index_article_id" ON "public"."digest_topic_article" USING btree( "article_id" );

DROP INDEX IF EXISTS "index_topic_id";
CREATE INDEX "index_topic_id" ON "public"."digest_topic_article" USING btree( "topic_id" );

DROP INDEX IF EXISTS "index_digest_id";
CREATE INDEX "index_digest_id" ON "public"."digest_topic_article" USING btree( "digest_id" );