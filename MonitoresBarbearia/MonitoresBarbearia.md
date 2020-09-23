**thread** cliente
```java
{
    Barbearia.corte_cabelo()
}
```

**thread** barbeiro
```java
{
    repita {
        Barbearia.pegue_proximo_cliente()
        // corta o cabelo do cliente
        Barbearia.termine_corte()
    }
}
```

## **variáveis do monitor**

#### **variáveis permanentes:**

 * **bool** barbeiro = false    true: significa que o barbeiro está esperando um cliente sentar-se na cadeira para corte de cabelo
 * **bool** cadeira = false     true: significa que o cliente está sentado na cadeira de corte, mas o barbeiro ainda não começou o corte
 * **bool** aberta = false      true: significa que a porta de saída foi aberta pelo barbeiro, mas o cliente atentido ainda não saiu da barbearia

#### **variáveis condicionais:**

 * **cond** barbeiro_disponivel cliente aguarda enquanto o barbeiro estiver ocupado sinalizando quando o barbeiro > 0
 * **cond** cadeira_ocupada     barbeiro aguarda enquanto a cadeira de corte estiver livre sinalizando quando cadeira >0
 * **cond** porta_aberta        cliente aguarda para **sair** enquanto a porta estiver fechada sinalizando quando aberta > 0
 * **cond** cliente_saiu        barbeiro aguarda enquanto o cliente atendtido não sair sinalizando quando aberta == 0

## **procedimento chamados pelo cliente**

```
**procedimento** corte_cabelo()
{
    **enquanto** ( barbeiro == 0 ) **wait** ( barbeiro_disponivel )
    barbeiro = barbeiro - 1
    cadeira = cadeira + 1
    **singnal** ( cadeira_ocupada )
    **enquanto** ( aberta == 0 ) **wait** ( porta_aberta )
    aberta = baerta - 1
    **signal** ( cliente_saiu )
}
```

## procedimentos chamados pelo barbeiro

```
**procedimento** pegue_proximo_cliente()
{
    barbeiro = barbeiro + 1
    **signal** ( barbeiro_disponivel )
    **enquanto** ( cadeira == 0 ) **wait** ( cadeira_ocupada )
    cadeira = cadeira - 1
}

**procedimento** termine_corte()
{
    aberta = aberta + 1
    **signal** ( porta_aberta )
    **enquanto** ( aberta > 0 ) **wait** ( cliente_saiu )
}
```

```java
private Lock gate_M = new ReentrantLock();
Condition gate_A = gate_M.newCondtion() // cadeir_ocupada
Condition gate_B = gate_M.newCondtion() // porta_aberta
Condition gate_C = gate_M.newCondtion() // barbeiro_diponivel
Condition gate_D = gate_M.newCondtion() // cliente_saiu
```