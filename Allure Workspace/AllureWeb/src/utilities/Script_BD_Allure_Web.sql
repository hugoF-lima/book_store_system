Use Allure

create table Contato_Cliente(
	Id_Cli_Contato BigInt primary key identity(1,1),
	Nome_Cli_Contato varchar(255),
	Email_Cli_Contato varchar(255),
	Assunto_Cli_Contato varchar(255),
	Mensagem_Cli_Contato varchar(255),
	Data_Cli_Contato datetime2(0) default getdate(),
);

select * from Contato_Cliente




