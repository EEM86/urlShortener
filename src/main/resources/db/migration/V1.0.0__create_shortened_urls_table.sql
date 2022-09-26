create table if not exists shortened_urls (
    id varchar(255) primary key,
    original_url varchar(255) not null unique,
    title varchar(255) not null,
    created_at timestamp not null default now()
)