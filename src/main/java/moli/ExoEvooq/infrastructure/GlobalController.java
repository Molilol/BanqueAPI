package moli.ExoEvooq.infrastructure;

import moli.ExoEvooq.infrastructure.persistance.ClientEntity;
import moli.ExoEvooq.service.ClientService;
import moli.ExoEvooq.vue.ClientDTO;
import moli.ExoEvooq.wrapper.WrapperDTOtoEntity;
import moli.ExoEvooq.wrapper.WrapperEntityToDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class GlobalController {

    @Autowired
    private ClientRepoHibernate clientRepoHibernate;
    @Autowired
    private WrapperEntityToDTO wrapperEntityToDTO;
    @Autowired
    private WrapperDTOtoEntity wrapperDTOtoEntity;
    @Autowired
    private ClientService clientService;


    @GetMapping(path = "clients/{name}")
    public ClientDTO getClientPerName(@PathVariable(value = "name") String name) {
        Optional<ClientEntity> client = clientRepoHibernate.findByName(name);
        ClientEntity clientEntity = client.get();
        ClientDTO clientDTO = wrapperEntityToDTO.clientEntityToClientDTO(clientEntity);
        return clientDTO;
    }

    @GetMapping(path = "clients")
    public List<ClientDTO> getClients() {
        List<ClientDTO> clientDTOList = new ArrayList<>();
        List<ClientEntity> clientEntityList = clientRepoHibernate.findAll();
        for (ClientEntity clientEntity : clientEntityList) {
            clientDTOList.add(wrapperEntityToDTO.clientEntityToClientDTO(clientEntity));
        }
        return clientDTOList;
    }

    @PostMapping(path = "/createClient")
    public void createClient(@RequestBody ClientDTO clientDTO) {
        ClientEntity clientEntity = wrapperDTOtoEntity.clientDTOtoClientEntity(clientDTO);
        clientService.addNewClient(clientEntity);
    }

    @PutMapping(path = "/updateClient")
    public void updateClient(@RequestBody ClientEntity clientEntity) {
        clientRepoHibernate.save(clientEntity);
    }

    @DeleteMapping(path = "/deleteClient/{name}")
    public void deleteClient(@PathVariable("name") String name) {
        ClientEntity clientEntity = clientRepoHibernate.findByName(name).get();
        clientRepoHibernate.delete(clientEntity);
    }






}
