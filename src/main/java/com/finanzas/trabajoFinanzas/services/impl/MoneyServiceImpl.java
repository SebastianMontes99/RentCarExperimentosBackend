package com.finanzas.trabajoFinanzas.services.impl;

import com.finanzas.trabajoFinanzas.entities.Money;
import com.finanzas.trabajoFinanzas.repositories.IMoneyRepository;
import com.finanzas.trabajoFinanzas.services.IMoneyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly=true)
public class MoneyServiceImpl implements IMoneyService {
    private final IMoneyRepository moneyRepository;

    public MoneyServiceImpl(IMoneyRepository moneyRepository) {
        this.moneyRepository = moneyRepository;
    }

    @Override
    public Money save(Money money) throws Exception {
        return moneyRepository.save(money);
    }

    @Override
    public void delete(Long id) throws Exception {
    moneyRepository.deleteById(id);
    }

    @Override
    public List<Money> getAll() throws Exception {
        return moneyRepository.findAll();
    }

    @Override
    public Optional<Money> getById(Long id) throws Exception {
        return moneyRepository.findById(id);
    }
}
