# Social Network API

Projeto em Spring Boot com login usando JWT feito para processo seletivo em Desenvolvedor Back-end. Este projeto implementa uma API para uam rede social com esquema baseado, mas não igual, no [{JSON} Placeholder](https://jsonplaceholder.typicode.com/). Este projeto usa Docker, PostgreSQL e Spring.


## Estrutura

### Docker e PostgreSQL

Para usar o PostgreSQL, preferi usar uma imagem do Docker juntamente com uma imagem para uma interface, o pdAdmin 4. A configuração para as duas imagens pode ser encontrada em `docker-compose.yml`.

A configuração para a imagem do PostgreSQL irá criar um banco de dados `socialnetwork` com usuário `root` e senha `root`. O endereço do banco é `localhost:5432`.

A configuração para a imagem do pgAdmin irá criar um usuário `admin@admin.com` com senha `root`. O pdAdmin pode ser acessado no navegador por `localhost:5050`.

O esquema do banco de dados para este projeto está no arquivo `dump.sql` e deve ser importado ao banco usando `psql`. Além disto, é necessário se conectar ao banco no pgAdmin. Sendo uma imagem acessando a outra, o endereço do banco será `db:5432`. Você também tem a opção de usar algum cliente próprio usando `localhost:5432`.

### API

O projeto da API está em `socialnetwork/` e está seprado nos seguintes pacotes:

- `config`: Possui as classes que configuram o Spring Security para usar JWT nas autenticações.

- `controller`: Possui as classes que definem os recursos da API.

- `exceptions`: Possui uma classe para ser usada como exceção.

- `model`: Possui os modelos de entidade do banco de daods e de requisições e respostas do JWT.

- `repository`: Possui as interfaces que conectam o banco de dados ao projeto. Utiliza `CrudRepository` do `Spring Data`.

- `service`: Possui as classes que tratam e provêm dados coletados pelos `repositories`. É o que conecta os `repositories` aos `controllers`.

## Modelo Relacional do banco

- users(id, name, email, username, password)
  - Chave primára: id
- posts(id, title, body, user_id)
  - Chave primária: id
  - user_id referencia user.id
- comments(id, body, post_id, user_id)]
  - Chave primária: id
  - post_id referencia post.id
  - user_id referencia user.id
- albums(user_id, id, title)
  - Chave primária: id
  - user_id referencia user.id
- photos(album_id, id, title, url, thumbnail_url)
  - Chave primária: id
  - album_id referencia album.id

Detalhes como os tipos dos atributos podem ser encontrados em `dump.sql`.

## API

Exemplo de requisições podem ser encontrados em `Social Network.postman_collection.json`. A descrição dos recursos é encontrada abaixo.

### POST /authenticate

Autenticação. Body é um JSON com campos `username` e `password`.

### POST /user

Cria um usuário. Body é um JSON com campos `username`, `name`, `password` e `email`.

### GET /user/{userId}/post

Retorna todos os posts de um usuário.

### GET /user/{userId}/album

Retorna todos os albums de um usuário.

### GET /post

Retorna todos os posts. Com o parâmetro `userId`, retorna todos os posts de um usuário.

### GET /post/{id}

Retorna um post pelo id.

### GET /post/{postId}/comments

Retorna todos os comentários de um post.

### POST /post

Cria um post. Body é um JSON com os campos `title` e `body`.

### GET /comments

Retorna todos os comentários. Com o parâmetro `postId`, retorna todos os comentários em um post.

### GET /comment/{id}

Retorna um comentário pelo id.

### POST /comment

Cria um comentário. Body é um JSON com os campos `postId`, `userId` e `body`.

### GET /album

Retorna todos os álbuns. Com o parâmetro `userId`, retorna todos os álbuns de um usuário.

### GET /album/{id}

Retorna um álbum pelo id.

### GET /album/{id}/photo

Retorna todas as fotos de um álbum.

### POST /album

Cria um álbum. Body é um JSON com o campo `title`.

### GET /photo

Retorna todas as fotos. Com o parâmetro `album_id` retorna todas as photos em um álbum.

### GET /photo/{id}

Retorna uma foto pelo id.

### POST /photo

Cria uma foto. Body é um JSON com os campos `albumId`, `title`, `url`, `thumbnail_url`.
