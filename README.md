# Http2 com grpc
- Utiliza proto como serialização e deserialização
- Existem 4 formas de comunicação
  - unary: cliente e servidor (uma request, um response)
  - server-streaming: fluxo de dados por parte do servidor (um request, recebe um fluxo de mensagens)
  - client-streaming: fluxo de dados por parte do cliente (envia um fluxo de mensagnes, espera um response)
  - bidirectional-streaming: fluxo de dados por parte de ambos (cliente e servidor). (envia um fluxo de mensagens, espera um fluxo de mensagens)
