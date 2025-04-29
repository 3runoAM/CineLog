<style>
.status-feito { color: green; }
.status-adiado { color: orange; }
.status-progresso { color: blue; }
</style>
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

### Requisitos Funcionais concluídos ou em progresso
- RF1: Cadastro de usuário com e-mail único, nome, senha. <b class="status-feito">[FEITO]</b>
- RF2: Login via:
  - e-mail/senha <b class="status-feito">[FEITO]</b>
  - OAuth2 (Google). <b class="status-adiado">[ADIADO]</b>
- RF3: Perfil de usuário com:
  - Biografia. <b class="status-feito">[FEITO]</b>
  - Estatísticas: <b class="status-progresso">[EM PROGRESSO]</b>
    - Filmes assistidos; <b class="status-feito">[FEITO]</b>
    - Resenhas publicadas; <b class="status-feito">[FEITO]</b>
    - Seguidores. <b class="status-progresso">[EM PROGRESSO]</b>
  - Foto de perfil (upload via arquivo): <b class="status-feito">[FEITO]</b>
    - JPEG ou PNG;
    - Limite de 2MB;
    - 4096x4096 pixels.
---

Desenvolvido por Bruno Martins (@3runoAM) ☕