# Http2 com grpc
- Utiliza proto como serialização e deserialização

## Funcionamento
- Aplicativo client chama o stub (chamada local)
- Stub chama o servidor gRPC
- Valor devolvido pelo servidor, passa para o client 

## Camadas
- Stub: client chama o servidor através de stubs. 
  - camada mais alta
  - gerada a partir de arquivos IDL (interface definition language)
  - arquivos possui extensão .proto 
- Transporte: camada mais baixa, utiliza protocolo http2

## Formas de comunicação
- Existem 4 formas de comunicação
  - unary: cliente e servidor (uma request, um response)
    O canal de comunicação se fecha, após enviar a resposta
  - server-streaming: fluxo de dados por parte do servidor (um request, recebe um fluxo de mensagens)
    Canal de comunicação do lado do servidor fica aberto, enquanto ouver eventos e serem emitidos.
  - client-streaming: fluxo de dados por parte do cliente (envia um fluxo de mensagnes, espera um response)
  - bidirectional-streaming: fluxo de dados por parte de ambos (cliente e servidor). (envia um fluxo de mensagens, espera um fluxo de mensagens)

# Load balance
- Quando se trabalha com load balance no estido round robin, quando o cliente envia um stream, precisamos trabalhar com subchannels, ou seja, o channel normal fica aberto apenas a uma instancia.
  - exemplo: client envia um stream, e esse fica vinculado a instancia 2 do server. 
