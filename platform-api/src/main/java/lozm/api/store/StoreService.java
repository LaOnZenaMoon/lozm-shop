package lozm.api.store;

import lombok.RequiredArgsConstructor;
import lozm.global.exception.ServiceException;
import lozm.object.dto.store.GetStoreDto;
import lozm.entity.store.Store;
import lozm.repository.store.StoreRepository;
import lozm.object.vo.store.StoreVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;


    public List<GetStoreDto> getStoreList() {
        return storeRepository.selectStoreList()
                .stream()
                .map(store -> GetStoreDto.builder()
                        .id(store.getId())
                        .name(store.getName())
                        .telNumber(store.getTelNumber())
                        .info(store.getInfo())
                        .build())
                .collect(Collectors.toList());
    }

    public Store findById(StoreVo storeVo) {
        return findStore(storeVo.getId()).get();
    }

    @Transactional
    public void save(StoreVo storeVo) {
        Store store = new Store();
        store.insertStore(storeVo);

        List<Store> findStoreNameDuplicated = storeRepository.findByName(store.getName());
        if(findStoreNameDuplicated.size() > 0) throw new ServiceException("STORE_0001", "Store name is duplicated.");

        storeRepository.save(store);
    }

    @Transactional
    public void update(StoreVo storeVo) {
        Optional<Store> findStore = findStore(storeVo.getId());
        findStore.get().updateStore(storeVo);
    }

    @Transactional
    public void delete(StoreVo storeVo) {
        Optional<Store> findStore = findStore(storeVo.getId());
        findStore.get().deleteStore(storeVo);
    }

    private Optional<Store> findStore(Long storeId) {
        Optional<Store> findStore = storeRepository.findById(storeId);
        findStore.orElseThrow(() -> new ServiceException("STORE_0002", "The store doesn't exist."));
        return findStore;
    }

}
