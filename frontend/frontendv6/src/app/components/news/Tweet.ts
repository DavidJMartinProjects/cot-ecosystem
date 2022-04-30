export class Tweet {
  id: string = "";
  createdAt: string = "";
  text: string = "";

  constructor(id: string, createdAt: string, text: string) {
      this.id = id;
      this.createdAt = createdAt;
      this.text = text;
  }

}
