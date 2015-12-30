DROP TABLE IF EXISTS article;
CREATE TABLE article (
    article_id serial PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    url VARCHAR(255) NOT NULL,
    description TEXT,
    created_date DATE default current_date,
    updated_date DATE
);
CREATE INDEX "title" ON "public"."article" USING btree( "title" Asc NULLS Last );
CREATE INDEX "description" ON "public"."article" USING btree( "description" Asc NULLS Last );

insert  INTO article (title, url) values ('title1', 'http://utl.com');
insert  INTO article (title, url) values ('title3', 'http://test.com');
insert  INTO article (title, url) values ('title3', 'http://test.com');