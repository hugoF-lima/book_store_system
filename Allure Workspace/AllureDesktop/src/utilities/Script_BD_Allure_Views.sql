Use Allure

Create view Func_Cli as
SELECT        dbo.Cliente.*, dbo.Funcionario.*
FROM            dbo.Cliente INNER JOIN
                         dbo.Funcionario ON dbo.Cliente.Id_func_fk_Cli = dbo.Funcionario.ID_func

select * from Func_Cli

create view Fornec_Func as
SELECT        dbo.Fornecedor.*, dbo.Funcionario.Nome_func
FROM            dbo.Fornecedor INNER JOIN
                         dbo.Funcionario ON dbo.Fornecedor.Id_func_fk_fornec = dbo.Funcionario.ID_func


Create view CupomInfo as
SELECT        dbo.Pedido_VendaRegistros.Data_venda, dbo.Pedido_VendaRegistros.valor_total, dbo.PagamentoVenda.valor_pago, dbo.PagamentoVenda.valor_troco, dbo.PedidoItem.id_produto_livro_fk_PedidoItem, 
                         dbo.PedidoItem.id_Pedido_VendaRegistro_fk_PedidoItem, dbo.PedidoItem.quantidade, dbo.PedidoItem.preco_item, dbo.Cliente.Nome_cli, dbo.Funcionario.Nome_func, dbo.PagamentoForma.nome_pagamento, 
                         dbo.Produto_Livro.Nome_Produto_livro
FROM            dbo.Pedido_VendaRegistros INNER JOIN
                         dbo.PagamentoVenda ON dbo.Pedido_VendaRegistros.Id_Pedido_VendaRegistro = dbo.PagamentoVenda.id_Pedido_VendaRegistro_fk_PagamentoVenda INNER JOIN
                         dbo.PedidoItem ON dbo.Pedido_VendaRegistros.Id_Pedido_VendaRegistro = dbo.PedidoItem.id_Pedido_VendaRegistro_fk_PedidoItem INNER JOIN
                         dbo.Produto_Livro ON dbo.PedidoItem.id_produto_livro_fk_PedidoItem = dbo.Produto_Livro.Id_Produto_livro INNER JOIN
                         dbo.Cliente ON dbo.Pedido_VendaRegistros.id_cli_fk_Venda = dbo.Cliente.ID_cli INNER JOIN
                         dbo.Funcionario ON dbo.Pedido_VendaRegistros.id_func_fk_Venda = dbo.Funcionario.ID_func INNER JOIN
                         dbo.PagamentoForma ON dbo.PagamentoVenda.id_pagamento_forma_fk_PagamentoVenda = dbo.PagamentoForma.id_forma_pagamento




Create View Venda_Func_Cli as
SELECT        dbo.Pedido_VendaRegistros.*, dbo.Funcionario.*, dbo.Cliente.*
FROM            dbo.Cliente INNER JOIN
                         dbo.Funcionario ON dbo.Cliente.Id_func_fk_Cli = dbo.Funcionario.ID_func INNER JOIN
                         dbo.Pedido_VendaRegistros ON dbo.Cliente.ID_cli = dbo.Pedido_VendaRegistros.id_cli_fk_Venda AND dbo.Funcionario.ID_func = dbo.Pedido_VendaRegistros.id_func_fk_Venda

select * from Venda_Func_Cli



SELECT        dbo.Cliente.*, dbo.Funcionario.*, dbo.Pedido_VendaRegistros.*
FROM            dbo.Cliente INNER JOIN
                         dbo.Funcionario ON dbo.Cliente.Id_func_fk_Cli = dbo.Funcionario.ID_func INNER JOIN
                         dbo.Pedido_VendaRegistros ON dbo.Cliente.ID_cli = dbo.Pedido_VendaRegistros.id_cli_fk_Venda AND dbo.Funcionario.ID_func = dbo.Pedido_VendaRegistros.id_func_fk_Venda


Create view Func_Cargo as
SELECT        dbo.Funcionario.*, dbo.Cargo.*
			 FROM            dbo.Cargo INNER JOIN
			                         dbo.Funcionario ON dbo.Cargo.	ID_Cargo = dbo.Funcionario.id_cargo_fk


Create view Livro_Genero_Fornec as
SELECT        dbo.Fornecedor.*, dbo.Genero_Livro.*, dbo.Produto_Livro.*
FROM            dbo.Fornecedor INNER JOIN
                         dbo.Produto_Livro ON dbo.Fornecedor.Id_fornec = dbo.Produto_Livro.Id_fornec_fk INNER JOIN
                         dbo.Genero_Livro ON dbo.Produto_Livro.Id_Genero_fk = dbo.Genero_Livro.Id_Genero

Create View VendaView as
SELECT        dbo.Pedido_VendaRegistros.Id_Pedido_VendaRegistro, dbo.Pedido_VendaRegistros.Data_venda, dbo.Pedido_VendaRegistros.valor_total, dbo.Funcionario.Nome_func, dbo.Cliente.Nome_cli
FROM            dbo.Cliente INNER JOIN
                         dbo.Funcionario ON dbo.Cliente.Id_func_fk_Cli = dbo.Funcionario.ID_func INNER JOIN
                         dbo.Pedido_VendaRegistros ON dbo.Cliente.ID_cli = dbo.Pedido_VendaRegistros.id_cli_fk_Venda AND dbo.Funcionario.ID_func = dbo.Pedido_VendaRegistros.id_func_fk_Venda


//-------

						   SELECT        *        FROM            dbo.Pedido_VendaRegistros INNER JOIN 
				dbo.PagamentoVenda ON dbo.Pedido_VendaRegistros.Id_Pedido_VendaRegistro = dbo.PagamentoVenda.id_Pedido_VendaRegistro_fk_PagamentoVenda INNER JOIN
				dbo.PedidoItem ON dbo.Pedido_VendaRegistros.Id_Pedido_VendaRegistro = dbo.PedidoItem.id_Pedido_VendaRegistro_fk_PedidoItem INNER JOIN 
				dbo.Produto_Livro ON dbo.PedidoItem.id_produto_livro_fk_PedidoItem = dbo.Produto_Livro.Id_Produto_livro INNER JOIN 
				dbo.Funcionario ON dbo.Pedido_VendaRegistros.id_func_fk_Venda = dbo.Funcionario.ID_func INNER JOIN 
				dbo.Cliente ON dbo.Pedido_VendaRegistros.id_cli_fk_Venda = dbo.Cliente.ID_cli INNER JOIN 
				dbo.PagamentoForma ON dbo.PagamentoVenda.id_pagamento_forma_fk_PagamentoVenda = dbo.PagamentoForma.id_forma_pagamento where Id_Pedido_VendaRegistro = 1



					   SELECT        *        FROM            dbo.Pedido_VendaRegistros INNER JOIN
				dbo.PagamentoVenda ON dbo.Pedido_VendaRegistros.Id_Pedido_VendaRegistro = dbo.PagamentoVenda.id_Pedido_VendaRegistro_fk_PagamentoVenda INNER JOIN
				dbo.PedidoItem ON dbo.Pedido_VendaRegistros.Id_Pedido_VendaRegistro = dbo.PedidoItem.id_Pedido_VendaRegistro_fk_PedidoItem INNER JOIN 
				dbo.Produto_Livro ON dbo.PedidoItem.id_produto_livro_fk_PedidoItem = dbo.Produto_Livro.Id_Produto_livro INNER JOIN 
				dbo.Funcionario ON dbo.Pedido_VendaRegistros.id_func_fk_Venda = dbo.Funcionario.ID_func INNER JOIN 
				dbo.Cliente ON dbo.Pedido_VendaRegistros.id_cli_fk_Venda = dbo.Cliente.ID_cli INNER JOIN 
				dbo.PagamentoForma ON dbo.PagamentoVenda.id_pagamento_forma_fk_PagamentoVenda = dbo.PagamentoForma.id_forma_pagamento where Id_Pedido_VendaRegistro = 2
				

SELECT        dbo.Pedido_VendaRegistros.Id_Pedido_VendaRegistro, dbo.Pedido_VendaRegistros.Data_venda,
dbo.Pedido_VendaRegistros.valor_total, dbo.Pedido_VendaRegistros.id_func_fk_Venda,
                         dbo.Pedido_VendaRegistros.id_cli_fk_Venda, dbo.Pedido_VendaRegistros.boleto_Venda,
dbo.PedidoItem.id_produto_livro_fk_PedidoItem, dbo.PedidoItem.id_Pedido_VendaRegistro_fk_PedidoItem,
                         dbo.PedidoItem.quantidade, dbo.PedidoItem.preco_item, dbo.Produto_Livro.Id_Produto_livro,
dbo.Produto_Livro.Id_Genero_fk, dbo.Produto_Livro.Nome_Produto_livro, dbo.Produto_Livro.ISBN_livro,
                         dbo.Produto_Livro.Autor_livro, dbo.Produto_Livro.Edicao_livro, dbo.Produto_Livro.Versao_livro,
dbo.Produto_Livro.Ano_livro, dbo.Produto_Livro.Preco_Livro, dbo.Produto_Livro.Quantidade_Livro,
                         dbo.Produto_Livro.Id_fornec_fk, dbo.Produto_Livro.Foto_livro,
dbo.Produto_Livro.Data_Insercao_Produto_Livro, dbo.Produto_Livro.Data_Resicao_Produto_Livro,
                         dbo.Produto_Livro.Ativo_Inativo_Status_Livro_Produto, dbo.Produto_Livro.Id_func_fk_produto_livro,
dbo.Genero_Livro.Id_Genero, dbo.Genero_Livro.Nome_Genero, dbo.Genero_Livro.Id_func_fk_genero,
                         dbo.PagamentoForma.id_forma_pagamento, dbo.PagamentoForma.nome_pagamento,
dbo.PagamentoVenda.id_pagamento_forma_fk_PagamentoVenda,
                         dbo.PagamentoVenda.id_Pedido_VendaRegistro_fk_PagamentoVenda, dbo.PagamentoVenda.valor_pago,
dbo.PagamentoVenda.valor_troco, dbo.Funcionario.ID_func, dbo.Funcionario.Nome_func,
                         dbo.Funcionario.Email_func, dbo.Funcionario.Tel_func, dbo.Funcionario.CPF_func,
dbo.Funcionario.Sexo_func, dbo.Funcionario.RG_func, dbo.Funcionario.Data_Nasc_func, dbo.Funcionario.CEP_func,
                         dbo.Funcionario.Logradouro_func, dbo.Funcionario.Numero_func, dbo.Funcionario.Complemento_func,
dbo.Funcionario.Bairro_func, dbo.Funcionario.Cidade_func, dbo.Funcionario.Estado_func,
                         dbo.Funcionario.Senha_func, dbo.Funcionario.Ativo_Inativo_Status, dbo.Funcionario.foto_func,
dbo.Funcionario.Data_Admissao_func, dbo.Funcionario.Data_Recisao_func, dbo.Funcionario.Login_Status,
                         dbo.Funcionario.id_cargo_fk, dbo.Cliente.ID_cli, dbo.Cliente.Nome_cli, dbo.Cliente.Email_cli,
dbo.Cliente.Tel_cli, dbo.Cliente.CPF_cli, dbo.Cliente.Sexo_cli, dbo.Cliente.RG_cli, dbo.Cliente.Data_Nasc_cli,
                         dbo.Cliente.CEP_cli, dbo.Cliente.Logradouro_cli, dbo.Cliente.Numero_cli,
dbo.Cliente.Complemento_cli, dbo.Cliente.Bairro_cli, dbo.Cliente.Cidade_cli, dbo.Cliente.Estado_cli,
dbo.Cliente.Data_Cadastro_cli,
                         dbo.Cliente.Ativo_Inativo_Status_cli, dbo.Cliente.Data_Resicao_Cli, dbo.Cliente.Id_func_fk_Cli
FROM            dbo.PagamentoVenda INNER JOIN
                         dbo.PagamentoForma ON dbo.PagamentoVenda.id_pagamento_forma_fk_PagamentoVenda =
dbo.PagamentoForma.id_forma_pagamento INNER JOIN
                         dbo.Pedido_VendaRegistros ON dbo.PagamentoVenda.id_Pedido_VendaRegistro_fk_PagamentoVenda =
dbo.Pedido_VendaRegistros.Id_Pedido_VendaRegistro INNER JOIN
                         dbo.Cliente INNER JOIN
                         dbo.Funcionario ON dbo.Cliente.Id_func_fk_Cli = dbo.Funcionario.ID_func INNER JOIN
                         dbo.Genero_Livro ON dbo.Funcionario.ID_func = dbo.Genero_Livro.Id_func_fk_genero ON
dbo.Pedido_VendaRegistros.id_cli_fk_Venda = dbo.Cliente.ID_cli AND
                         dbo.Pedido_VendaRegistros.id_func_fk_Venda = dbo.Funcionario.ID_func INNER JOIN
                         dbo.PedidoItem ON dbo.Pedido_VendaRegistros.Id_Pedido_VendaRegistro =
dbo.PedidoItem.id_Pedido_VendaRegistro_fk_PedidoItem INNER JOIN
                         dbo.Produto_Livro ON dbo.Funcionario.ID_func = dbo.Produto_Livro.Id_func_fk_produto_livro AND
dbo.Genero_Livro.Id_Genero = dbo.Produto_Livro.Id_Genero_fk AND
                         dbo.PedidoItem.id_produto_livro_fk_PedidoItem = dbo.Produto_Livro.Id_Produto_livro where id_Pedido_VendaRegistro_fk_PedidoItem = 1


SELECT        dbo.Pedido_VendaRegistros.Id_Pedido_VendaRegistro, dbo.PagamentoVenda.valor_pago, dbo.PagamentoVenda.valor_troco, dbo.Produto_Livro.Nome_Produto_livro, dbo.PedidoItem.quantidade, 
                         dbo.PedidoItem.preco_item, dbo.Cliente.Nome_cli, dbo.Funcionario.Nome_func, dbo.PagamentoForma.nome_pagamento, dbo.Pedido_VendaRegistros.Data_venda, dbo.Pedido_VendaRegistros.valor_total, 
                         dbo.Produto_Livro.Id_Produto_livro, dbo.Produto_Livro.Preco_Livro, dbo.PagamentoVenda.id_Pedido_VendaRegistro_fk_PagamentoVenda
FROM            dbo.PagamentoVenda INNER JOIN
                         dbo.PagamentoForma ON dbo.PagamentoVenda.id_pagamento_forma_fk_PagamentoVenda = dbo.PagamentoForma.id_forma_pagamento INNER JOIN
                         dbo.Pedido_VendaRegistros ON dbo.PagamentoVenda.id_Pedido_VendaRegistro_fk_PagamentoVenda = dbo.Pedido_VendaRegistros.Id_Pedido_VendaRegistro INNER JOIN
                         dbo.Cliente INNER JOIN
                         dbo.Funcionario ON dbo.Cliente.Id_func_fk_Cli = dbo.Funcionario.ID_func ON dbo.Pedido_VendaRegistros.id_cli_fk_Venda = dbo.Cliente.ID_cli AND 
                         dbo.Pedido_VendaRegistros.id_func_fk_Venda = dbo.Funcionario.ID_func INNER JOIN
                         dbo.PedidoItem ON dbo.Pedido_VendaRegistros.Id_Pedido_VendaRegistro = dbo.PedidoItem.id_Pedido_VendaRegistro_fk_PedidoItem INNER JOIN
                         dbo.Produto_Livro ON dbo.Funcionario.ID_func = dbo.Produto_Livro.Id_func_fk_produto_livro AND dbo.PedidoItem.id_produto_livro_fk_PedidoItem = dbo.Produto_Livro.Id_Produto_livro

						 select id_Func_fk_Venda, id_cli_fk_Venda from Pedido_VendaRegistros

						 select id_Func_fk_Venda, id_cli_fk_Venda from teste
						 

Create view FuncCargoNumber as
select count(Funcionario.Id_Cargo_fk) as NumFunc from Funcionario RIGHT OUTER JOIN
	dbo.Cargo On dbo.Cargo.Id_Cargo = dbo.Funcionario.Id_Cargo_fk
	group by Funcionario.Id_Cargo_fk


create View FuncCargoNumber as
	
SELECT        COUNT(IsNull(dbo.Cargo.ID_Cargo, 0)) AS NumFunc, dbo.Cargo.Nome_Cargo, dbo.Cargo.Salario_Cargo, dbo.Cargo.Descricao_Cargo, dbo.Cargo.Data_Admissao_Cargo, dbo.Cargo.ID_Cargo, 
                         dbo.Cargo.Ativo_Inativo_Status_Cargo
FROM            dbo.Cargo JOIN
                         dbo.Funcionario ON dbo.Funcionario.id_cargo_fk = dbo.Cargo.ID_Cargo
GROUP BY dbo.Cargo.ID_Cargo, dbo.Cargo.Nome_Cargo, dbo.Cargo.Salario_Cargo, dbo.Cargo.Descricao_Cargo, dbo.Cargo.Data_Admissao_Cargo, dbo.Cargo.Ativo_Inativo_Status_Cargo




SELECT        COUNT(IsNull(dbo.Cargo.ID_Cargo, 0)) AS NumFunc, dbo.Cargo.Nome_Cargo, dbo.Cargo.Salario_Cargo, dbo.Cargo.Descricao_Cargo, dbo.Cargo.Data_Admissao_Cargo, dbo.Cargo.ID_Cargo, 
                         dbo.Cargo.Ativo_Inativo_Status_Cargo
FROM            dbo.Cargo JOIN
                         dbo.Funcionario ON dbo.Funcionario.id_cargo_fk = dbo.Cargo.ID_Cargo
GROUP BY dbo.Cargo.ID_Cargo, dbo.Cargo.Nome_Cargo, dbo.Cargo.Salario_Cargo, dbo.Cargo.Descricao_Cargo, dbo.Cargo.Data_Admissao_Cargo, dbo.Cargo.Ativo_Inativo_Status_Cargo where dbo.Cargo.Nome_Cargo like '%o%'

Update Cargo set Ativo_Inativo_Status_Cargo = 'Inativo' where ID_Cargo = 5

SELECT COUNT(*) FROM (
    SELECT COUNT(Funcionario.Id_Cargo_fk) AS actCount FROM Funcionario
    INNER JOIN Cargo ON Funcionario.Id_Cargo_fk  = Cargo.Id_Cargo
    GROUP BY Funcionario.Id_Cargo_fk) As t


	SELECT        dbo.Pedido_VendaRegistros.*, dbo.Funcionario.*, dbo.Cliente.*
FROM            dbo.Cliente INNER JOIN 
                         dbo.Funcionario ON dbo.Cliente.Id_func_fk_Cli = dbo.Funcionario.ID_func INNER JOIN
                         dbo.Pedido_VendaRegistros ON dbo.Cliente.ID_cli = dbo.Pedido_VendaRegistros.id_cli_fk_Venda AND dbo.Funcionario.ID_func = dbo.Pedido_VendaRegistros.id_func_fk_Venda
 where Nome_cli like '%a%' AND iD_cli > 1 and Ativo_Inativo_Status_cli = 'Ativo'



 SELECT        dbo.Pedido_VendaRegistros.Id_Pedido_VendaRegistro, dbo.Pedido_VendaRegistros.Data_venda, dbo.Pedido_VendaRegistros.valor_total, dbo.Funcionario.Nome_func, dbo.Cliente.Nome_cli 
FROM            dbo.Cliente INNER JOIN
                         dbo.Funcionario ON dbo.Cliente.Id_func_fk_Cli = dbo.Funcionario.ID_func INNER JOIN 
                         dbo.Pedido_VendaRegistros ON dbo.Cliente.ID_cli = dbo.Pedido_VendaRegistros.id_cli_fk_Venda AND dbo.Funcionario.ID_func = dbo.Pedido_VendaRegistros.id_func_fk_Venda
 where "Id_Pedido_VendaRegistro" like '%1%'