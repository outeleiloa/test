import React, { Component } from "react";
import { withRouter } from "react-router-dom"
import { Table, Card, CardBody, Form, Input, Button } from "reactstrap";
import ReactTable from "react-table";
import "react-table/react-table.css";

class TitlePage extends Component {
    constructor(props) {
      super(props);
      this.state = {
        data: undefined,
        ownerChangeValue: "",
        descriptionChangeValue: "",
        titleNumChangeValue: "",
      }
      this.loadTitle = this.loadTitle.bind(this);
      this.ownerNameHandleChange = this.ownerNameHandleChange.bind(this);
      this.descriptionHandleChange = this.descriptionHandleChange.bind(this);
      this.titleNumHandleChange = this.titleNumHandleChange.bind(this);
      this.handleSubmit = this.handleSubmit.bind(this);
    }
    ownerNameHandleChange(event) {
      this.setState({ownerChangeValue: event.target.value});
    }
    descriptionHandleChange(event) {
      this.setState({descriptionChangeValue: event.target.value});
    }
    titleNumHandleChange(event) {
      this.setState({titleNumChangeValue: event.target.value});
    }
    handleSubmit(event) {
      event.preventDefault();
      fetch(`/api/titles/${this.state.data.id}`, {
          method: 'POST',
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({
              ownerName: this.state.ownerChangeValue,
              description: this.state.descriptionChangeValue,
              titleNumber: this.state.titleNumChangeValue,
          }),
        })
        .then(res => res.json())
        .then(json => {
          console.log(json)
          this.setState({
            data: json,
            ownerChangeValue: '',
            descriptionChangeValue: '',
            titleNumChangeValue: '',
          })
        });
    }
    loadTitle() {
      var titleNo = this.props.match.params.titleNo;
      fetch(`/api/titles/${titleNo}`)
        .then(res => res.json())
        .then(json => {
          console.log(json)
          this.setState({
            data: json,
          })
        });
    }
    componentDidMount() {
      this.loadTitle();
    }
    componentDidUpdate(newProps) {
      var titleNo = this.props.match.params.titleNo;
      if(this.state.data && this.state.data.id != titleNo) {
        this.loadTitle();
      }
    }
    render() {
      var titleNo = this.props.match.params.titleNo;
      var title = this.state.data;
      const columns = [{
          Header: 'Title Number',
          accessor: 'titleNumber'
      },{
          Header: 'Owner',
          accessor: 'ownerName'
      },{
          Header: 'Description',
          accessor: 'description'
      },{
          Header: 'Time',
          accessor: 'time'
      }]
      return (
        <div>
          <h3>Title #{titleNo}</h3>
          {this.state.data === undefined && <p>
            Loading...
          </p>}
          {this.state.data && <div>
            <Table>
                <tbody>
                    <tr>
                        <th>Description</th>
                        <td>{title.description}</td>
                    </tr>
                    <tr>
                        <th>Current Owner</th>
                        <td>{title.ownerName}</td>
                    </tr>
                </tbody>
            </Table>
            <Card color="light" style={{marginTop: "50px"}}>
                <CardBody>
                    <h4>Change Owner</h4>
                    <p>As a registered conveyancing lawyer, you may record a change of ownership of this title.</p>
                    <p>You may also update the Title Description and Title Number.</p>
                    <Form inline onSubmit={this.handleSubmit}>
                        <Input type="text" value={this.state.ownerChangeValue} onChange={this.ownerNameHandleChange}
                            placeholder="Enter the new owner name" style={{width: "320px"}}/>
                        <Input type="text" value={this.state.descriptionChangeValue} onChange={this.descriptionHandleChange}
                            placeholder="Title Description" style={{width: "760px"}}/>
                        <Input type="text" value={this.state.titleNumChangeValue} onChange={this.titleNumHandleChange}
                            placeholder="Title Number" style={{width: "120px"}}/>
                        &nbsp;
                        <Button color="primary" type="submit" value="Submit">Save</Button>
                    </Form>
                </CardBody>
            </Card>
            <h4 style={{marginTop: "25px"}}>Title History</h4>
            <ReactTable
                data={title.journal}
                columns={columns}
                defaultPageSize = {3}
                pageSizeOptions = {[3, 6, 12]}
                className = "-striped -highlight"
            />
          </div>}
        </div>
      );
    }
}

export default withRouter(TitlePage);
