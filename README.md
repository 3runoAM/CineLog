# CineLog API (Letterboxd clone)

Bem-vindo ao repositório da **CineLog API**, uma plataforma social para entusiastas de cinema! Esta API permite que usuários registrem filmes assistidos, escrevam resenhas, criem listas temáticas, sigma outros usuários e descubram novos films... Tudo isso com integração à **TMDB API** para obter dados completos sobre filmes.

## Funcionalidades Principais 🚀

- **Gestão de Usuários**: Cadastro, login (via e-mail/senha ou OAuth2 com Google), e perfis personalizados com foto, biografia e estatísticas.
- **Catálogo de Filmes**: Integração com a TMDB API para buscar informações detalhadas sobre filmes (título, diretor, ano, gênero, sinopse, pôster).
- **Registro e Resenhas**: Usuários podem marcar filmes como "Assistidos", adicionar notas e escrever resenhas.
- **Interação Social**: Seguir outros usuários, curtir e comentar resenhas e listas
- **Listas Temáticas**: Criar e compartilhar listas de filmes (ex: "Melhores filmes de terror dos anos 80").
- **Notificações**: Receba notificações em tempo real sobre novos seguidores, curtidas e comentários.
- **Painel de Administração**: Gerenciamento de usuários, moderação de conteúdo e administração de filmes adicionados manualmente.

## Tecnologias Utilizadas 💻

- **Banco de Dados**: PostgreSQL e H2 DataBase.
- **Autenticação**: Spring Security + JWT.
- **Cache**: Redis para melhorar o desempenho de endpoints populares.
- **APIs**: RESTful API com documentação Swagger/OpenAPI.
- **Testes**: Unitários (JUnit/Mockito) e de integração (Testcontainers).

## Requisitos Não Funcionais ⚙️

- **Desempenho**: Tempo de resposta médio < 500ms para 90% das requisições.
- **Segurança**: Senhas armazenadas com BCrypt, HTTPS obrigatório e proteção contra SQL Injection e XSS.
- **Escalabilidade**: Projetada para escalar horizontalmente com Redis.
- **Confiabilidade**: Backup diário do banco de dados e monitoramento com Prometheus/Grafana.

## Licença 📄

Este projeto está licenciado sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## Status do Projeto
W.I.P. 💻

---

Desenvolvido por Bruno Martins (@3runoAM) ☕