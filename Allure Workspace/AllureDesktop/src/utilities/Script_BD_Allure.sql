CREATE DATABASE Allure;
GO
--drop database Allure
use Allure
create table Cliente(
ID_cli int primary key identity(1,1),
Nome_cli varchar (100) not null,
Email_cli varchar (75) null,
Tel_cli varchar (15) not null,
CPF_cli varchar (25) not null,
Sexo_cli char (1) not null,
RG_cli varchar (15),
Data_Nasc_cli date not null,
CEP_cli varchar(20) not null,
Logradouro_cli varchar (50) not null,
Numero_cli varchar (10) null,
Complemento_cli varchar (50) null,
Bairro_cli varchar (40) not null,
Cidade_cli varchar (30) not null,
Estado_cli char (2) not null,
Data_Cadastro_cli datetime2(0) default getdate(),
Ativo_Inativo_Status_cli varchar (255) not null,
Data_Resicao_Cli datetime2(0) default getdate(),
);


create table Cargo(
ID_Cargo int identity(1,1) primary key,
Nome_Cargo varchar (25),
Salario_Cargo  float not null,
Descricao_Cargo varchar(500),
AcessoSistema_Permissao varchar (255),
Cadastro_Consulta_Cli_Permissao varchar(255),
Cadastro_Consulta_Func_Permissao varchar(255),
Cadastro_Consulta_Cargo_Permissao varchar(255),
Cadastro_Consulta_Fornec_Permissao varchar(255),
Cadastro_Consulta_Prod_Livro_Permissao varchar(255),
Cadastro_Consulta_Contato_Cliente_Permissao varchar(255),
Vendas_Permissao varchar(255),
Ativo_Inativo_Status_Cargo varchar (25) not null,
Data_Admissao_Cargo datetime2(0) default getdate(),
Data_Resicao_Cargo datetime2(0) default getdate()
);


create table Funcionario(
ID_func int primary key identity(1,1),
Nome_func varchar (100) not null,
Email_func varchar (75) null,
Tel_func varchar (15) not null,
CPF_func varchar (20) not null,
Sexo_func char (1) not null,
RG_func varchar (15) not null,
Data_Nasc_func date not null,
CEP_func varchar (20) not null,
Logradouro_func varchar (50) not null,
Numero_func varchar (10) null,
Complemento_func varchar (50) null,
Bairro_func varchar (40) not null,
Cidade_func varchar (30) not null,
Estado_func char (2) not null,
Senha_func varchar (25) not null,
Ativo_Inativo_Status varchar (255) not null,
foto_func varbinary (max),
Data_Admissao_func datetime2(0) default getdate(),
Data_Recisao_func datetime2(0) default getdate(),
Login_Status varchar (255) not null,
id_cargo_fk int foreign key references cargo(id_cargo) not null,
);


Alter table Cliente add Id_func_fk_Cli int references Funcionario(ID_func)
Alter table Cargo add Id_Func_fk_Cargo int references Funcionario (Id_Func)

insert into Cargo values('Gerente', 1600.00, 'Responsavel por administrar a Empresa/Loja', 'Yes', 'Yes', 'Yes', 'Yes', 'Yes', 'Yes', 'Yes', 'Yes', 'Ativo', GETDATE(), null, null)
insert into Cargo values ('Operador de Caixa', 800.00, 'Responsavel por lidar com valores de vendas e serviços', 'Yes', 'Yes', 'No', 'No', 'No', 'No', 'No', 'Yes', 'Ativo', GETDATE(), null, null)

insert into Funcionario values ('John Miller', 'millerjohn@gmail.com', '(11)96584848', '447.046.564-03', 'M', '2977269', '1978-06-11', '06447-170', 'Avenida Marginal Direita', '365', 'Bloco 27, Ap 21', 'Jardim Paulista', 'Barueri', 'SP', '10101010', 'Ativo', null, GETDATE(), null, 'Offline', '1')
insert into Cliente values ('Balcão', 'nome@exemplo.com', '(00)00000000', '000.000.000-00', ' ', '000000000', '11-11-1111', '00000-000', 'AAAAA','00000','AAAAAA', 'aaaaa', 'aaaaaa', '  ', GETDATE(), 'Ativo', null, '1')


create table Fornecedor(
Id_fornec int not null identity(1,1) primary key,
Nome_fornec varchar (200) not null,
Email_fornec varchar (300),
Tel_fornec varchar (25) not null,
CNPJ_fornec varchar (35) not null,
CEP_fornec varchar (20) not null,
Logradouro_fornec varchar (50) not null,
Numero_fornec varchar (10) null,
Complemento_fornec varchar (50) null,
Bairro_fornec varchar (40) not null,
Cidade_fornec varchar (30) not null,
Estado_fornec char (2) not null,
Ativo_Inativo_Status_fornec varchar (25),
Data_Admissao_fornec datetime2(0) default getdate(),
Data_Recisao_fornec datetime2(0) default getdate(),
Id_func_fk_fornec int references Funcionario (ID_func)
);


create table Genero_Livro(
Id_Genero int identity (1,1) primary key,
Nome_Genero varchar (255) not null,
);

insert Genero_Livro values ('Romance')
insert Genero_Livro values ('Literatura')
insert Genero_Livro values ('Filosofia')
insert Genero_Livro values ('Terror')
insert Genero_Livro values ('Fantasia/Ficção')
insert Genero_Livro values ('Historia')


create table Produto_Livro(
Id_Produto_livro int identity (1,1) primary key,
Id_Genero_fk int references Genero_Livro(Id_Genero),
Nome_Produto_livro varchar (255) not null,
ISBN_livro varchar (25) not null,
Autor_livro varchar (50) not null,
Edicao_livro varchar (50) not null,
Ano_livro int not null,
Preco_Livro float not null,
Quantidade_Livro int not null,
Id_fornec_fk int references Fornecedor (Id_fornec),
Foto_livro varbinary(max),
Data_Insercao_Produto_Livro datetime2(0) default getdate(),
Data_Resicao_Produto_Livro datetime2(0) default getdate(),
Ativo_Inativo_Status_Livro_Produto varchar (50) not null,
Id_func_fk_produto_livro int references Funcionario (ID_func)
);


create table Pedido_VendaRegistros (
Id_Pedido_VendaRegistro int primary key not null,
Data_venda datetime2(0) not null default getdate(),
valor_total float not null,
id_func_fk_Venda int references Funcionario(id_func) ,
id_cli_fk_Venda int references Cliente (id_cli),
boleto_Venda varbinary(max),
);


create table PedidoItem(	
id_produto_livro_fk_PedidoItem int references Produto_Livro(id_produto_livro),
id_Pedido_VendaRegistro_fk_PedidoItem int references Pedido_VendaRegistros (id_Pedido_VendaRegistro),
quantidade int not null,
preco_item float not null,
);


create table PagamentoForma (
id_forma_pagamento int identity(1,1) primary key,
nome_pagamento varchar(255) not null
);

INSERT INTO PagamentoForma values('Dinheiro')
INSERT INTO PagamentoForma values('Cartão')
INSERT INTO PagamentoForma values('Débito')

create table PagamentoVenda(
id_pagamento_forma_fk_PagamentoVenda int references PagamentoForma (id_forma_pagamento) not null,
id_Pedido_VendaRegistro_fk_PagamentoVenda int references Pedido_VendaRegistros(id_Pedido_VendaRegistro) not null,
valor_pago float not null,
valor_troco float,
);