package org.darrazi.ebank;

import org.darrazi.ebank.exceptions.CustomerNotFoundException;
import org.darrazi.ebank.dtos.BankAccountDTO;
import org.darrazi.ebank.dtos.CurrentBankAccountDTO;
import org.darrazi.ebank.dtos.CustomerDTO;
import org.darrazi.ebank.dtos.SavingBankAccountDTO;

import org.darrazi.ebank.services.BankAccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class EBankingApplication {

    public static void main(String[] args) {
        SpringApplication.run(EBankingApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(BankAccountService bankAccountService){
        return args -> {
           Stream.of("Anas","Ayoub","Mariam").forEach(name->{
               CustomerDTO customer=new CustomerDTO();
               customer.setName(name);
               customer.setEmail(name+"@gmail.com");
               bankAccountService.saveCustomer(customer);
           });
           bankAccountService.listCustomers().forEach(customer->{
               try {
                   bankAccountService.saveCurrentBankAccount(Math.random()*90000,9000,customer.getId());
                   bankAccountService.saveSavingBankAccount(Math.random()*120000,5.5,customer.getId());

               } catch (CustomerNotFoundException e) {
                   e.printStackTrace();
               }
           });
            List<BankAccountDTO> bankAccounts = bankAccountService.bankAccountList();
            for (BankAccountDTO bankAccount:bankAccounts){
                for (int i = 0; i <10 ; i++) {
                    String accountId;
                    if(bankAccount instanceof SavingBankAccountDTO){
                        accountId=((SavingBankAccountDTO) bankAccount).getId();
                    } else{
                        accountId=((CurrentBankAccountDTO) bankAccount).getId();
                    }
                    bankAccountService.credit(accountId,10000+Math.random()*120000,"Credit");
                    bankAccountService.debit(accountId,1000+Math.random()*9000,"Debit");
                }
            }
        };
    }

}
