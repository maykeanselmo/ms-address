## MS Address

O MS Address tem a responsabilidade de armazenar os endereços e vinculá-los a um usuário.

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
