import React from "react";
import logo from "./logo.svg";
import "./App.css";
import "bootstrap/dist/css/bootstrap.min.css";
import {
  Table,
  Button,
  Container,
  Modal,
  ModalHeader,
  ModalBody,
  FormGroup,
  ModalFooter,
} from "reactstrap";
import axios from "axios";


const url = 'http://localhost:9050/ms-crud-customer/v1/customer/'

class App extends React.Component {
  state = {
    data: [],
    modalActualizar: false,
    modalInsertar: false,
    form: {
      IdCustomer: "",
      FirstName: "",
      LastName: "",
      Email: "",
      PhoneNumber: "",
      BirthDate: ""
    },
  };

  componentDidMount() {
    this.listarAPi()
  }
  componentDidUpdate() {
  }

  listarAPi = () => {
    axios.get(url + 'list').then(response => {
      console.log(response.data)
      this.setState({
        data: response.data
      })
    })
  }

  agregarApi = (dato) => {
    axios.post(url + 'add', dato).then(response => {
      console.log(response.data)
    })
  }

  actualizarApi = (dato) => {
    axios.post(url + 'update', dato).then(response => {
      console.log(response.data)
    })
  }

  eliminarApi = (dato) => {
    axios.get(url + 'delete/' + dato).then(response => {
      console.log(response.data)
    })
  }

  mostrarModalActualizar = (dato) => {
    this.setState({
      form: dato,
      modalActualizar: true,
    });
  };

  cerrarModalActualizar = () => {
    this.setState({ modalActualizar: false });
  };

  mostrarModalInsertar = () => {
    this.setState({
      modalInsertar: true,
    });
  };

  cerrarModalInsertar = () => {
    this.setState({ modalInsertar: false });
  };

  editar = (dato) => {
    var contador = 0;
    var arreglo = this.state.data;
    arreglo.map((registro) => {
      if (dato.IdCustomer == registro.IdCustomer) {
        arreglo[contador].FirstName = dato.FirstName;
        arreglo[contador].LastName = dato.LastName;
        arreglo[contador].Email = dato.Email;
        arreglo[contador].PhoneNumber = dato.PhoneNumber;
        arreglo[contador].BirthDate = dato.BirthDate;
        this.actualizarApi(arreglo[contador]);
      }
      contador++;
    });
    this.setState({ data: arreglo, modalActualizar: false });
    
  };

  eliminar = (dato) => {
    var opcion = window.confirm("You want to delete the customer " + dato.IdCustomer);
    if (opcion == true) {
      var contador = 0;
      var arreglo = this.state.data;
      arreglo.map((registro) => {
        if (dato.IdCustomer == registro.IdCustomer) {
          this.eliminarApi(dato.IdCustomer);
          arreglo.splice(contador, 1);
        }
        contador++;
      });
      this.setState({ data: arreglo, modalActualizar: false });
    }
  };

  insertar = () => {
    var valorNuevo = {...this.state.form};
    valorNuevo.IdCustomer = this.state.data.length + 1;
    var lista = this.state.data;
    lista.push(valorNuevo);
    console.log(valorNuevo)
    this.agregarApi(valorNuevo)
    this.setState({ modalInsertar: false, data: lista });
  }

  handleChange = (e) => {
    this.setState({
      form: {
        ...this.state.form,
        [e.target.name]: e.target.value,
      },
    });
  };

  render() {
    return (
      <>
        <Container>
        <br />
          <Button color="success" onClick={() => this.mostrarModalInsertar()}>Add Customer</Button>
          <br />
          <br />
          <Table>
            <thead>
              <tr>
                <th>ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Phone Number</th>
                <th>Birth Date</th>
                <th>Action</th>
              </tr>
            </thead>

            <tbody>
              {this.state.data.map((dato) => (
                <tr key={dato.IdCustomer}>
                  <td>{dato.IdCustomer}</td>
                  <td>{dato.FirstName}</td>
                  <td>{dato.LastName}</td>
                  <td>{dato.Email}</td>
                  <td>{dato.PhoneNumber}</td>
                  <td>{dato.BirthDate}</td>
                  <td>
                    <Button
                      color="primary"
                      onClick={() => this.mostrarModalActualizar(dato)}
                    >
                      Update
                    </Button>{" "}
                    <Button color="danger" onClick={()=> this.eliminar(dato)}>Delete</Button>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        </Container>

        <Modal isOpen={this.state.modalActualizar}>
          <ModalHeader>
           <div><h3>Update Customer</h3></div>
          </ModalHeader>

          <ModalBody>
            <FormGroup>
              <label>
               Id:
              </label>
            
              <input
                className="form-control"
                readOnly
                type="text"
                value={this.state.form.IdCustomer}
              />
            </FormGroup>
            
            <FormGroup>
              <label>
                First Name: 
              </label>
              <input
                className="form-control"
                name="FirstName"
                type="text"
                onChange={this.handleChange}
                value={this.state.form.FirstName}
              />
            </FormGroup>
            
            <FormGroup>
              <label>
                Last Name: 
              </label>
              <input
                className="form-control"
                name="LastName"
                type="text"
                onChange={this.handleChange}
                value={this.state.form.LastName}
              />
            </FormGroup>

            <FormGroup>
              <label>
                Email: 
              </label>
              <input
                className="form-control"
                name="Email"
                type="text"
                onChange={this.handleChange}
                value={this.state.form.Email}
              />
            </FormGroup>
            
            <FormGroup>
              <label>
                Phone Number: 
              </label>
              <input
                className="form-control"
                name="PhoneNumber"
                type="text"
                onChange={this.handleChange}
                value={this.state.form.PhoneNumber}
              />
            </FormGroup>
            
            <FormGroup>
              <label>
                Birth Date: 
              </label>
              <input
                className="form-control"
                name="BirthDate"
                type="text"
                onChange={this.handleChange}
                value={this.state.form.BirthDate}
              />
            </FormGroup>
          </ModalBody>

          <ModalFooter>
            <Button
              color="primary"
              onClick={() => this.editar(this.state.form)}
            >
              Update
            </Button>
            <Button
              color="danger"
              onClick={() => this.cerrarModalActualizar()}
            >
              Cancel
            </Button>
          </ModalFooter>
        </Modal>



        <Modal isOpen={this.state.modalInsertar}>
          <ModalHeader>
           <div><h3>Add Customer</h3></div>
          </ModalHeader>

          <ModalBody>
            <FormGroup>
              <label>
                Id: 
              </label>
              
              <input
                className="form-control"
                readOnly
                type="text"
                value={this.state.data.length+1}
              />
            </FormGroup>
            
            <FormGroup>
              <label>
                First Name: 
              </label>
              <input
                className="form-control"
                name="FirstName"
                type="text"
                onChange={this.handleChange}
              />
            </FormGroup>
            
            <FormGroup>
              <label>
                Last Name: 
              </label>
              <input
                className="form-control"
                name="LastName"
                type="text"
                onChange={this.handleChange}
              />
            </FormGroup>

            <FormGroup>
              <label>
                Email: 
              </label>
              <input
                className="form-control"
                name="Email"
                type="text"
                onChange={this.handleChange}
              />
            </FormGroup>
            
            <FormGroup>
              <label>
                Phone Number: 
              </label>
              <input
                className="form-control"
                name="PhoneNumber"
                type="text"
                onChange={this.handleChange}
              />
            </FormGroup>
            
            <FormGroup>
              <label>
                Birth Date: 
              </label>
              <input
                className="form-control"
                name="BirthDate"
                type="text"
                onChange={this.handleChange}
              />
            </FormGroup>
          </ModalBody>

          <ModalFooter>
            <Button
              color="primary"
              onClick={() => this.insertar()}
            >
              Add
            </Button>
            <Button
              className="btn btn-danger"
              onClick={() => this.cerrarModalInsertar()}
            >
              Cancel
            </Button>
          </ModalFooter>
        </Modal>
      </>
    );
  }
}
export default App;
