## MS Address

O MS Address tem a responsabilidade de armazenar os endereços e vinculá-los a um usuário.
## Tecnologias utilizadas
- Java JDK 17

### Dependências
- Spring Boot 3
- Spring Web
- Spring Data JPA
- Spring DevTools
- ModelMapper
- Lombok
- Spring Cloud OpenFeign
- Banco de dados MongoDB

### Funcionalidades

- Encapsular a API do ViaCep (https://viacep.com.br/) para obtenção de informações de endereço.

### Validações Necessárias

- Ao receber uma nova requisição, o microserviço deve verificar se o CEP existe no banco de dados. Caso não exista, deve consumir a API ViaCep para obter as informações de endereço.

### Endpoints

- **POST /{cep}**
  - Cria um novo endereço a partir do CEP fornecido.
  - Exemplo de Payload: 
    ```json
    {
      "cep": "69999-999"
    }
    ```
  - Retorna o ID do endereço criado.
  
- **GET /{id}**
  - Retorna as informações de um endereço específico com base no ID.
  - Exemplo de Payload de Resposta:
    ```json
    {
      "cep": "69999-999",
      "logradouro": "Rua Exemplo",
      "complemento": "Bairro Exemplo",
      "localdiade": "Cidade Exemplo",
      "uf": "EX"
    }
    ```

### Comunicação com o MS User

A comunicação entre o MS User e o MS Address é realizada através do OpenFeign. Quando um usuário é criado, seu CEP é enviado para o microserviço de address via requisição Feign. Lá, é feita uma consulta no banco de dados próprio do MS Address à procura do endereço relacionado a esse CEP. Se não for encontrado, uma consulta à API ViaCep é realizada para obter o endereço correspondente ao CEP e armazená-lo no banco de dados do MS Address.

## Problemas


Durante o desenvolvimento do projeto, enfrentei desafios significativos, especialmente ao lidar com a implementação da autenticação usando JWT e a integração da mensageria. Esses processos exigiram um esforço extra, pois surgiram dúvidas complexas que demandaram tempo para serem compreendidas e resolvidas adequadamente.

Inicialmente, planejei estabelecer a comunicação entre os serviços por meio de mensageria. Contudo, após uma cuidadosa análise e discussões, concluí que a abordagem assíncrona entre os microserviços de usuário e endereço poderia não ser a mais eficiente. Diante disso, optei por uma mudança estratégica para uma comunicação síncrona, aproveitando a tecnologia OpenFeign. Essa decisão não apenas exigiu ajustes na arquitetura, mas também afetou a implementação dos outros microserviços, impactando a validação e o tratamento de exceções no microserviço de endereço.

Essas adversidades foram oportunidades valiosas de aprendizado, ressaltando a importância de uma abordagem flexível diante dos desafios e da necessidade de adaptação durante o processo de desenvolvimento. A reflexão e a capacidade de ajustar estratégias foram cruciais para garantir a entrega funcional do projeto. É importante ressaltar que esses desafios também contribuíram para a falta de validação, tratamento de exceções e testes no microserviço de endereço, bem como para a decisão de manter o banco de dados como SQL, em vez de migrá-lo para a AWS.
