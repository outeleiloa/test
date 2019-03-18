import React, { Component } from "react";
import withRouter from "react-router-dom/withRouter";
import { Form, Label, Input, Button } from "reactstrap";

class TitleSearch extends Component {
    constructor(props) {
      super(props);
      this.state = {
        value: '',
      }
      this.handleChange = this.handleChange.bind(this);
      this.handleSubmit = this.handleSubmit.bind(this);
    }
    handleChange(event) {
      this.setState({value: event.target.value});
    }
    handleSubmit(event) {
      this.props.history.push(`/titles/${this.state.value}`);
    }
    render() {
      return (
        <div style={{display: "inline-block"}}>
          <Form inline onSubmit={this.handleSubmit}>
            <Input type="text" value={this.state.value} onChange={this.handleChange} placeholder="Enter a title number" />
            &nbsp;
            <Button color="primary" type="submit" value="Submit">Go</Button>
          </Form>
        </div>
      );
    }
  }

  export default withRouter(TitleSearch);