package com.devrobattini.dslist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devrobattini.dslist.dto.GameDTO;
import com.devrobattini.dslist.dto.GameMinDTO;
import com.devrobattini.dslist.entities.Game;
import com.devrobattini.dslist.projections.GameMinProjections;
import com.devrobattini.dslist.repositories.GameRepository;

@Service
public class GameService {
	
	@Autowired
	private GameRepository gameRepository;

	@Transactional(readOnly = true)
	public GameDTO findById(Long id) {
		Game result = gameRepository.findById(id).get();
		return new GameDTO(result);
	}
	
	@Transactional(readOnly = true)
	public List<GameMinDTO> findAll(){
		List<Game> result = gameRepository.findAll();
		return result.stream().map(GameMinDTO::new).toList();
	}
	
	@Transactional(readOnly = true)
	public List<GameMinDTO> findByList(Long listId){
		List<GameMinProjections> result = gameRepository.searchByList(listId);
		return result.stream().map(GameMinDTO::new).toList();
	}
	
}
