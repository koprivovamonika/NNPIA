import axios from 'axios';

const USER_API_BASE_URL = 'http://localhost:8080/';

class TodoService {

    getTodos(){
        return axios.get(USER_API_BASE_URL+'todos');
    }


}

export default new TodoService();